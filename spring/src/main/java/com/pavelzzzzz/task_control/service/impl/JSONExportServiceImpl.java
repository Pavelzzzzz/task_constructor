package com.pavelzzzzz.task_control.service.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocExceptionBuilder;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.JSONExport;
import com.pavelzzzzz.task_control.hibernate.repository.JSONExportRepository;
import com.pavelzzzzz.task_control.rest.dto.JSONExportDto;
import com.pavelzzzzz.task_control.rest.dto.JSONExportForSaveDto;
import com.pavelzzzzz.task_control.service.api.JSONExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JSONExportServiceImpl implements JSONExportService {

    @Autowired
    private JSONExportRepository jsonExportRepository;

    @Override
    public List<JSONExportDto> findAll(Pageable pageable) {
        return jsonExportRepository.findAll(pageable).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public JSONExportDto getById(Integer id) throws PocNotFoundException {
        return fromEntity(
                jsonExportRepository.findById(id)
                        .orElseThrow(() -> PocExceptionBuilder
                                .createPocNotFoundException(JSONExportDto.class, id)));
    }

    @Override
    public JSONExportDto create(JSONExportForSaveDto jsonExport) throws PocException {
        return fromEntity(
                jsonExportRepository.save(
                        toEntity(jsonExport, null)));
    }

    @Override
    public void delete(Integer id) throws PocNotFoundException {
        jsonExportRepository.delete(jsonExportRepository.findById(id)
                .orElseThrow(() -> PocExceptionBuilder
                        .createPocNotFoundException(JSONExportDto.class, id)));

    }

    @Override
    public JSONExportDto fromEntity(JSONExport jsonExport) {
        return JSONExportDto.builder()
                .id(jsonExport.getId())
                .title(jsonExport.getTitle())
                .body(jsonExport.getBody())
                .createdAt(jsonExport.getCreatedAt())
                .build();
    }

    @Override
    public JSONExport toEntity(JSONExportForSaveDto jsonExportForSaveDto, JSONExport oldEntity) throws PocException {
        return JSONExport.builder()
                .title(jsonExportForSaveDto.getTitle())
                .body(jsonExportForSaveDto.getBody())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
