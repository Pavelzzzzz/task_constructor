package com.pavelzzzzz.task_control.rest.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.dto.JSONExportDto;
import com.pavelzzzzz.task_control.rest.dto.JSONExportForSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * JSONExportController rest endpoint
 */

@RequestMapping("/api/JSONExport")
public interface JSONExportController {

  @GetMapping
  @Operation(summary = "returns list JSONExportDto")
  List<JSONExportDto> list(int page, int size, String sortDir, String sort);

  @GetMapping("/{id}")
  @Operation(summary = "returns FileDto by id ")
  JSONExportDto get(Integer id) throws PocNotFoundException;

  @PostMapping
  @Operation(summary = "create JSONExportDto")
  JSONExportDto create(JSONExportForSaveDto jsonExportForSaveDto) throws PocException;

  @DeleteMapping("/{id}")
  @Operation(summary = "delete JSONExportDto by id")
  ResponseEntity delete(Integer id) throws PocNotFoundException;
}
