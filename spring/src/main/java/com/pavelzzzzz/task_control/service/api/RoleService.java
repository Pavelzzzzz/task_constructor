package com.pavelzzzzz.task_control.service.api;

import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.Role;
import com.pavelzzzzz.task_control.rest.dto.RoleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService extends EntityTransform<RoleDto, Object, Role>{

  List<RoleDto> findAll(Pageable pageable);

  RoleDto getById(int id) throws PocNotFoundException;
}
