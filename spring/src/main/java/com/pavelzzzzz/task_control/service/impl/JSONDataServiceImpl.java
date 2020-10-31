package com.pavelzzzzz.task_control.service.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocExceptionBuilder;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.JSONData;
import com.pavelzzzzz.task_control.hibernate.repository.JSONDataRepository;
import com.pavelzzzzz.task_control.rest.dto.JSONDataDto;
import com.pavelzzzzz.task_control.rest.dto.JSONDataForSaveDto;
import com.pavelzzzzz.task_control.service.api.JSONDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JSONDataServiceImpl implements JSONDataService {

    @Autowired
    private JSONDataRepository jsonDataRepository;

    @Override
    public List<JSONDataDto> findAll(Pageable pageable) {
        return jsonDataRepository.findAll(pageable).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public JSONDataDto getById(Integer id) throws PocNotFoundException {
        return fromEntity(
                jsonDataRepository.findById(id)
                        .orElseThrow(() -> PocExceptionBuilder
                                .createPocNotFoundException(JSONDataDto.class, id)));
    }

    @Override
    public JSONDataDto create(JSONDataForSaveDto jsonData) throws PocException {
        return fromEntity(
                jsonDataRepository.save(
                        toEntity(jsonData, null)));
    }

    @Override
    public void delete(Integer id) throws PocNotFoundException {
        jsonDataRepository.delete(jsonDataRepository.findById(id)
                .orElseThrow(() -> PocExceptionBuilder
                        .createPocNotFoundException(JSONDataDto.class, id)));

    }

    @Override
    public JSONDataDto fromEntity(JSONData jsonData) {
        return JSONDataDto.builder()
                .id(jsonData.getId())
                .title(jsonData.getTitle())
                .body(jsonData.getBody())
                .folderId(jsonData.getFolderId())
                .createdAt(jsonData.getCreatedAt())
                .build();
    }

    @Override
    public JSONData toEntity(JSONDataForSaveDto jsonDataForSaveDto, JSONData oldEntity) throws PocException {
        return JSONData.builder()
                .title(jsonDataForSaveDto.getTitle())
                .body(jsonDataForSaveDto.getBody())
                .folderId(jsonDataForSaveDto.getFolderId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
