package com.pavelzzzzz.task_control.rest.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.api.JSONExportController;
import com.pavelzzzzz.task_control.rest.dto.JSONExportDto;
import com.pavelzzzzz.task_control.rest.dto.JSONExportForSaveDto;
import com.pavelzzzzz.task_control.service.api.JSONExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JSONExportControllerImpl implements JSONExportController {

    @Autowired
    private JSONExportService jsonExportService;

    @Override
    public List<JSONExportDto> list(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                    @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                    @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir,
                                    @RequestParam(value = "sort", defaultValue = "Id", required = false) String sort) {
        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return jsonExportService.findAll(pageReq);
    }

    @Override
    public JSONExportDto get(@PathVariable Integer id) throws PocNotFoundException {
        return jsonExportService.getById(id);
    }

    @Override
    public JSONExportDto create(@RequestBody JSONExportForSaveDto jsonExportForSaveDto) throws PocException {
        return jsonExportService.create(jsonExportForSaveDto);
    }

    @Override
    public ResponseEntity delete(@PathVariable Integer id) throws PocNotFoundException {
        jsonExportService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
