package com.pavelzzzzz.task_control.security.service.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocExceptionBuilder;
import com.pavelzzzzz.task_control.hibernate.entity.Role;
import com.pavelzzzzz.task_control.hibernate.entity.User;
import com.pavelzzzzz.task_control.hibernate.entity.UserPassword;
import com.pavelzzzzz.task_control.hibernate.repository.RoleRepository;
import com.pavelzzzzz.task_control.hibernate.repository.UserPasswordRepository;
import com.pavelzzzzz.task_control.hibernate.repository.UserRepository;
import com.pavelzzzzz.task_control.rest.dto.RoleDto;
import com.pavelzzzzz.task_control.rest.dto.UserDto;
import com.pavelzzzzz.task_control.security.service.CustomUserDetails;
import com.pavelzzzzz.task_control.security.service.api.CustomUserDetailsService;
import com.pavelzzzzz.task_control.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPasswordRepository passwordRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return toCustomUserDetails(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username)));
    }

    @Override
    public UserDto create(CustomUserDetails customUserDetails) throws PocException {
        UserDto userDto = fromEntity(
                userRepository.save(
                        toEntity(customUserDetails, null)));
        passwordRepository.save(
                UserPassword.builder()
                        .userId(userDto.getId())
                        .password(passwordEncoder.encode(customUserDetails.getPassword()))
                        .build());
        return userDto;
    }

    @Override
    public UserDto findByUsernameAndCheckPassword(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        UserPassword userPassword = passwordRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("No password"));
        if (passwordEncoder.matches(password, userPassword.getPassword())){
            return fromEntity(user);
        }
        return null;
    }

    private CustomUserDetails toCustomUserDetails(User user){
        return CustomUserDetails.builder()
                .username(user.getUsername())
                .roleDtoSet(user.getRoles().stream()
                        .map(role -> roleService.fromEntity(role))
                        .collect(Collectors.toSet()))
                .password(passwordRepository.findById(user.getId())
                        .orElseThrow(() -> new UsernameNotFoundException("No password"))
                        .getPassword())
                .isEnabled(user.isEnabled())
                .build();
    }

    @Override
    public User toEntity(CustomUserDetails customUserDetails, User oldEntity) throws PocException {
        Optional<RoleDto> notExistsRoleDto = customUserDetails.getRoleDtoSet().stream()
                .filter(roleDto -> !roleRepository.existsById(roleDto.getId()))
                .findFirst();
        if (notExistsRoleDto.isPresent()) {
            throw PocExceptionBuilder.createPocNotFoundException(RoleDto.class, notExistsRoleDto.get().getId());
        }

        return User.builder()
                .username(customUserDetails.getUsername())
                .enabled(customUserDetails.isEnabled())
                .roles(new HashSet<>(
                        (Collection<Role>) roleRepository.findAllById(
                                customUserDetails.getRoleDtoSet().stream()
                                        .map(RoleDto::getId)
                                        .collect(Collectors.toSet())))
                )
                .build();
    }

    @Override
    public UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .isEnabled(user.isEnabled())
                .roles(user.getRoles().stream()
                        .map(role -> roleService.fromEntity(role))
                        .collect(Collectors.toSet()))
                .build();
    }
}
