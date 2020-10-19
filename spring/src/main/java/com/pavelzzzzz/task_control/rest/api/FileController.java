package com.pavelzzzzz.task_control.rest.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.dto.FileDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * FileController rest endpoint
 */

@RequestMapping("/api/file")
public interface FileController {

  @GetMapping
  @Operation(summary = "returns list FileDto")
  List<FileDto> list(int page, int size, String sortDir, String sort);

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(summary = "upload file")
  FileDto uploadFile(MultipartFile file) throws PocException;

  @GetMapping("/{id}")
  @Operation(summary = "returns FileDto by id ")
  FileDto get(Integer id) throws PocNotFoundException;

  @GetMapping("/{id}/download")
  @Operation(summary = "download file by id ")
  ResponseEntity<byte []> download(Integer id) throws PocNotFoundException;

  @DeleteMapping("/{id}")
  @Operation(summary = "delete file by id")
  ResponseEntity delete(Integer id) throws PocNotFoundException;
}
