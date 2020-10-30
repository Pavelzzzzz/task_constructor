package com.pavelzzzzz.task_control.service.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocExceptionBuilder;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.File;
import com.pavelzzzzz.task_control.hibernate.repository.FileRepository;
import com.pavelzzzzz.task_control.rest.dto.FileDto;
import com.pavelzzzzz.task_control.service.api.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileDto getById(Integer id) throws PocNotFoundException {
        return fromEntity(
                fileRepository.findById(id)
                        .orElseThrow(() -> PocExceptionBuilder
                                .createPocNotFoundException(FileDto.class, id)));
    }

    @Override
    public List<FileDto> findAll(Pageable pageable) {
        return fileRepository.findAll(pageable).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public byte[] download(Integer id) throws PocNotFoundException {
        return fileRepository.findById(id)
                .orElseThrow(() -> PocExceptionBuilder
                        .createPocNotFoundException(FileDto.class, id))
                .getData();
    }

    @Override
    public FileDto create(MultipartFile file) throws PocException {
        return fromEntity(
                fileRepository.save(
                        toEntity(file, null)));
    }

    @Override
    public void delete(Integer id) throws PocNotFoundException {
        fileRepository.delete(fileRepository.findById(id)
                .orElseThrow(() -> PocExceptionBuilder
                        .createPocNotFoundException(FileDto.class, id)));
    }

    @Override
    public FileDto fromEntity(File file) {
        return FileDto.builder()
                .id(file.getId())
                .name(file.getName())
                .type(file.getType())
                .size(file.getData().length)
                .createdAt(file.getCreatedAt())
                .build();
    }

    @Override
    public File toEntity(MultipartFile multipartFile, File oldEntity)
            throws PocException {
        try {
            return File.builder()
                    .name(StringUtils.cleanPath(multipartFile.getOriginalFilename()))
                    .type(multipartFile.getContentType())
                    .data(multipartFile.getBytes())
                    .createdAt(new Timestamp(System.currentTimeMillis()))
                    .build();
        } catch (IOException e) {
            throw PocExceptionBuilder.createPocException(e.getMessage());
        }
    }
}
