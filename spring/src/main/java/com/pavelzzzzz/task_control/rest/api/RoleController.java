package com.pavelzzzzz.task_control.rest.api;

import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.dto.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * RoleController rest endpoint
 */
@RequestMapping("/api/roles")
public interface RoleController {

  @GetMapping
  @Operation(summary = "returns list RoleDto")
  List<RoleDto> list(int page, int size, String sortDir, String sort);

  @GetMapping("/{id}")
  @Operation(summary = "returns RoleDto by id ")
  RoleDto get(Integer id) throws PocNotFoundException;
}
