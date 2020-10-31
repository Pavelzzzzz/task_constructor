package com.pavelzzzzz.task_control.service.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocExceptionBuilder;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.Folder;
import com.pavelzzzzz.task_control.hibernate.repository.FolderRepository;
import com.pavelzzzzz.task_control.rest.dto.FolderDto;
import com.pavelzzzzz.task_control.rest.dto.FolderForSaveDto;
import com.pavelzzzzz.task_control.service.api.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Override
    public List<FolderDto> findAll(Pageable pageable) {
        return folderRepository.findAll(pageable).stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FolderDto getById(Integer id) throws PocNotFoundException {
        return fromEntity(
                folderRepository.findById(id)
                        .orElseThrow(() -> PocExceptionBuilder
                                .createPocNotFoundException(FolderDto.class, id)));
    }

    @Override
    public FolderDto create(FolderForSaveDto folderForSaveDto) throws PocException {
        return fromEntity(
                folderRepository.save(
                        toEntity(folderForSaveDto, null)));
    }

    @Override
    public FolderDto update(Integer id, FolderForSaveDto folder) throws PocException {
        return fromEntity(
                folderRepository.save(toEntity(folder, folderRepository.findById(id)
                        .orElseThrow(() -> PocExceptionBuilder
                                .createPocNotFoundException(FolderDto.class, id)))));
    }

    @Override
    public void delete(Integer id) throws PocNotFoundException {
        if (folderRepository.existsById(id)){
            folderRepository.deleteById(id);
        } else {
            throw PocExceptionBuilder.createPocNotFoundException(FolderDto.class, id);
        }
    }

    @Override
    public FolderDto fromEntity(Folder folder) {
        return FolderDto.builder()
                .id(folder.getId())
                .title(folder.getTitle())
                .parentId(folder.getParentId())
                .createdAt(folder.getCreatedAt())
                .updatedAt(folder.getUpdatedAt())
                .build();
    }

    @Override
    public Folder toEntity(FolderForSaveDto folderForSaveDto, Folder oldEntity) throws PocException {
        if (folderForSaveDto.getParentId() != null && !folderRepository.existsById(folderForSaveDto.getParentId())) {
            throw PocExceptionBuilder.createPocNotFoundException(FolderDto.class, folderForSaveDto.getParentId());
        }
        return Folder.builder()
                .id(oldEntity != null ? oldEntity.getId() : null)
                .title(oldEntity != null && folderForSaveDto.getTitle() == null
                        ? oldEntity.getTitle() : folderForSaveDto.getTitle())
                .parentId(oldEntity != null && folderForSaveDto.getParentId() == null
                        ? oldEntity.getParentId() : folderForSaveDto.getParentId())
                .createdAt(oldEntity != null
                        ? oldEntity.getCreatedAt() : new Timestamp(System.currentTimeMillis()))
                .updatedAt(oldEntity != null
                        ? new Timestamp(System.currentTimeMillis()) : null)
                .build();
    }
}
