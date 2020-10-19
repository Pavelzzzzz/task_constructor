package com.pavelzzzzz.task_control.service.impl;

import com.pavelzzzzz.task_control.exception.PocExceptionBuilder;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.Role;
import com.pavelzzzzz.task_control.hibernate.repository.RoleRepository;
import com.pavelzzzzz.task_control.rest.dto.RoleDto;
import com.pavelzzzzz.task_control.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public List<RoleDto> findAll(Pageable pageable) {
    return roleRepository.findAll(pageable).stream()
        .map(this::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public RoleDto getById(Integer id) throws PocNotFoundException {
    return fromEntity(
        roleRepository.findById(id)
            .orElseThrow(() -> PocExceptionBuilder
                .createPocNotFoundException(RoleDto.class, id)));
  }

  @Override
  public RoleDto fromEntity(Role role) {
    return RoleDto.builder()
        .roleId(role.getRoleId())
        .roleName(role.getRoleName())
        .build();
  }

  @Override
  public Role toEntity(Object role, Role oldEntity)
      throws PocNotFoundException {
    //TODO
    return null;
  }
}
