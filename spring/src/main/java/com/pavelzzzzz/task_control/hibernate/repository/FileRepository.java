package com.pavelzzzzz.task_control.hibernate.repository;

import com.pavelzzzzz.task_control.hibernate.entity.File;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FileRepository extends PagingAndSortingRepository<File, Integer> {
}
