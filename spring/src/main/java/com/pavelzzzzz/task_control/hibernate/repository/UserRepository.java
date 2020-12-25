package com.pavelzzzzz.task_control.hibernate.repository;

import com.pavelzzzzz.task_control.hibernate.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
