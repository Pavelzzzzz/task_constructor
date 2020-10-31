package com.pavelzzzzz.task_control.rest.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.api.JSONDataController;
import com.pavelzzzzz.task_control.rest.dto.JSONDataDto;
import com.pavelzzzzz.task_control.rest.dto.JSONDataForSaveDto;
import com.pavelzzzzz.task_control.service.api.JSONDataService;
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
public class JSONDataControllerImpl implements JSONDataController {

    @Autowired
    private JSONDataService jsonDataService;

    @Override
    public List<JSONDataDto> list(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                  @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                  @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir,
                                  @RequestParam(value = "sort", defaultValue = "Id", required = false) String sort) {
        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return jsonDataService.findAll(pageReq);
    }

    @Override
    public JSONDataDto get(@PathVariable Integer id) throws PocNotFoundException {
        return jsonDataService.getById(id);
    }

    @Override
    public JSONDataDto create(@RequestBody JSONDataForSaveDto jsonDataForSaveDto) throws PocException {
        return jsonDataService.create(jsonDataForSaveDto);
    }

    @Override
    public ResponseEntity delete(@PathVariable Integer id) throws PocNotFoundException {
        jsonDataService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
