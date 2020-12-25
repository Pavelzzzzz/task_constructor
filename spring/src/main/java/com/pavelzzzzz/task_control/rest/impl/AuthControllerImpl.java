package com.pavelzzzzz.task_control.rest.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.rest.api.AuthController;
import com.pavelzzzzz.task_control.rest.dto.AuthRequest;
import com.pavelzzzzz.task_control.rest.dto.RegistrationRequest;
import com.pavelzzzzz.task_control.rest.dto.RoleDto;
import com.pavelzzzzz.task_control.rest.dto.UserDto;
import com.pavelzzzzz.task_control.security.service.CustomUserDetails;
import com.pavelzzzzz.task_control.security.service.JwtProvider;
import com.pavelzzzzz.task_control.security.service.api.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
public class AuthControllerImpl implements AuthController {
    @Autowired
    private CustomUserDetailsService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserDto registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) throws PocException {
        CustomUserDetails newUser = CustomUserDetails.builder()
                .username(registrationRequest.getUsername())
                .password(registrationRequest.getPassword())
                .roleDtoSet(
                        Collections.singleton(
                                RoleDto.builder().id(2).name("User").build()))
                .isEnabled(true)
                .build();
        return userService.create(newUser);
    }

    @Override
    public ResponseEntity<String> auth(@RequestBody AuthRequest request) {
        UserDto userDto = userService.findByUsernameAndCheckPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userDto.getUsername());
        return ResponseEntity.ok().body(token);
    }
}
