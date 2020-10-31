package com.pavelzzzzz.task_control.rest.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.dto.FolderDto;
import com.pavelzzzzz.task_control.rest.dto.FolderForSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * FolderController rest endpoint
 */

@RequestMapping("/api/folder")
public interface FolderController {

  @GetMapping
  @Operation(summary = "returns list of FolderDto")
  List<FolderDto> list(int page, int size, String sortDir, String sort);

  @GetMapping("/{id}")
  @Operation(summary = "returns FolderDto by id ")
  FolderDto get(Integer id) throws PocNotFoundException;

  @PostMapping
  @Operation(summary = "create FolderDto")
  FolderDto create(FolderForSaveDto folderForSaveDto) throws PocException;

  @PostMapping("/{id}")
  @Operation(summary = "update and returns FolderDto by id ")
  FolderDto update(Integer id, FolderForSaveDto folder) throws PocException;

  @DeleteMapping("/{id}")
  @Operation(summary = "delete FolderDto by id")
  ResponseEntity delete(Integer id) throws PocNotFoundException;
}
