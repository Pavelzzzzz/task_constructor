package com.pavelzzzzz.task_control.service.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.JSONExport;
import com.pavelzzzzz.task_control.rest.dto.JSONExportDto;
import com.pavelzzzzz.task_control.rest.dto.JSONExportForSaveDto;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface JSONExportService extends EntityTransform<JSONExportDto, JSONExportForSaveDto, JSONExport> {

    List<JSONExportDto> findAll(Pageable pageable);

    JSONExportDto getById(Integer id) throws PocNotFoundException;

    JSONExportDto create(JSONExportForSaveDto jsonExport) throws PocException;

    void delete(Integer id) throws PocNotFoundException;
}
