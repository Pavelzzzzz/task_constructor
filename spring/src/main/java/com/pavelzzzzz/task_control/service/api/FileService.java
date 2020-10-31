package com.pavelzzzzz.task_control.service.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.File;
import com.pavelzzzzz.task_control.rest.dto.FileDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService extends EntityTransform<FileDto, MultipartFile, File> {

    List<FileDto> findAll(Pageable pageable);

    FileDto getById(Integer id) throws PocNotFoundException;

    byte[] download(Integer id) throws PocNotFoundException;

    FileDto create(MultipartFile file) throws PocException;

    void delete(Integer id) throws PocNotFoundException;
}
