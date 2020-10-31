package com.pavelzzzzz.task_control.rest.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.dto.JSONDataDto;
import com.pavelzzzzz.task_control.rest.dto.JSONDataForSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * JSONDataController rest endpoint
 */

@RequestMapping("/api/JSONData")
public interface JSONDataController {

  @GetMapping
  @Operation(summary = "returns list of JSONDataDto")
  List<JSONDataDto> list(int page, int size, String sortDir, String sort);

  @GetMapping("/{id}")
  @Operation(summary = "returns JSONDataDto by id ")
  JSONDataDto get(Integer id) throws PocNotFoundException;

  @PostMapping
  @Operation(summary = "create JSONDataDto")
  JSONDataDto create(JSONDataForSaveDto jsonDataForSaveDto) throws PocException;

  @DeleteMapping("/{id}")
  @Operation(summary = "delete JSONDataDto by id")
  ResponseEntity delete(Integer id) throws PocNotFoundException;
}
