package com.pavelzzzzz.task_control.service.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.hibernate.entity.Folder;
import com.pavelzzzzz.task_control.rest.dto.FolderDto;
import com.pavelzzzzz.task_control.rest.dto.FolderForSaveDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FolderService extends EntityTransform<FolderDto, FolderForSaveDto, Folder> {

    List<FolderDto> findAll(Pageable pageable);

    List<FolderDto> findAllParentId(Integer parentId, Pageable pageable) throws PocNotFoundException;

    FolderDto getById(Integer id) throws PocNotFoundException;

    FolderDto create(FolderForSaveDto folderForSaveDto) throws PocException;

    FolderDto update(Integer id, FolderForSaveDto folder) throws PocException;

    void delete(Integer id) throws PocNotFoundException;
}
