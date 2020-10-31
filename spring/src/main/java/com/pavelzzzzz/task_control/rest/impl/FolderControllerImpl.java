package com.pavelzzzzz.task_control.rest.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.api.FolderController;
import com.pavelzzzzz.task_control.rest.dto.FolderDto;
import com.pavelzzzzz.task_control.rest.dto.FolderForSaveDto;
import com.pavelzzzzz.task_control.service.api.FolderService;
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
public class FolderControllerImpl implements FolderController {

    @Autowired
    private FolderService folderService;

    @Override
    public List<FolderDto> list(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir,
                                @RequestParam(value = "sort", defaultValue = "title", required = false) String sort) {
        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return folderService.findAll(pageReq);
    }

    @Override
    public FolderDto get(@PathVariable Integer id) throws PocNotFoundException {
        return folderService.getById(id);
    }

    @Override
    public FolderDto create(@RequestBody FolderForSaveDto folderForSaveDto) throws PocException {
        return folderService.create(folderForSaveDto);
    }

    @Override
    public FolderDto update(@PathVariable Integer id,
                            @RequestBody FolderForSaveDto folder) throws PocException {
        return folderService.update(id, folder);
    }

    @Override
    public ResponseEntity delete(@PathVariable Integer id) throws PocNotFoundException {
        folderService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
