package com.pavelzzzzz.task_control.service.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.JSONData;
import com.pavelzzzzz.task_control.rest.dto.JSONDataDto;
import com.pavelzzzzz.task_control.rest.dto.JSONDataForSaveDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JSONDataService extends EntityTransform<JSONDataDto, JSONDataForSaveDto, JSONData> {

    List<JSONDataDto> findAll(Pageable pageable);

    JSONDataDto getById(Integer id) throws PocNotFoundException;

    JSONDataDto create(JSONDataForSaveDto jsonData) throws PocException;

    void delete(Integer id) throws PocNotFoundException;
}
