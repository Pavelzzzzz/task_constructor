package com.pavelzzzzz.task_control.hibernate.repository;

import com.pavelzzzzz.task_control.hibernate.entity.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FolderRepository extends PagingAndSortingRepository<Folder, Integer> {

    Page<Folder> findAllByParentId(Integer parentId, Pageable pageable);
}
