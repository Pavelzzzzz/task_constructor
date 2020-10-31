package com.pavelzzzzz.task_control.rest.impl;

import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.api.RoleController;
import com.pavelzzzzz.task_control.rest.dto.RoleDto;
import com.pavelzzzzz.task_control.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleControllerImpl implements RoleController {

  @Autowired
  private RoleService roleService;

  @Override
  public List<RoleDto> list(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir,
                            @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {
    PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
    return roleService.findAll(pageReq);
  }

  @Override
  public RoleDto get(@PathVariable Integer id) throws PocNotFoundException {
    return roleService.getById(id);
  }
}
