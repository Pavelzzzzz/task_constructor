package com.pavelzzzzz.task_control.rest.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.api.FileController;
import com.pavelzzzzz.task_control.rest.dto.FileDto;
import com.pavelzzzzz.task_control.service.api.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileControllerImpl implements FileController {

    @Autowired
    private FileService fileService;

    @Override
    public List<FileDto> list(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                              @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                              @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir,
                              @RequestParam(value = "sort", defaultValue = "Id", required = false) String sort) {
        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return fileService.findAll(pageReq);
    }

    @Override
    public FileDto uploadFile(@RequestPart("file") MultipartFile file) throws PocException {
        //TODO: set folder
        return fileService.create(file);
    }

    @Override
    public FileDto get(@PathVariable Integer id) throws PocNotFoundException {
        return fileService.getById(id);
    }

    @Override
    public ResponseEntity<byte[]> download(@PathVariable Integer id) throws PocNotFoundException {
        FileDto fileDto = fileService.getById(id);
        byte[] fileData = fileService.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getName() + "\"")
                .body(fileData);
    }

    @Override
    public ResponseEntity delete(@PathVariable Integer id) throws PocNotFoundException {
        fileService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
