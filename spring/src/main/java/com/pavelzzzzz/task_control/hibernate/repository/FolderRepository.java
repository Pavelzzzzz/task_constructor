package com.pavelzzzzz.task_control.hibernate.repository;

import com.pavelzzzzz.task_control.hibernate.entity.Folder;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FolderRepository extends PagingAndSortingRepository<Folder, Integer> {
}
