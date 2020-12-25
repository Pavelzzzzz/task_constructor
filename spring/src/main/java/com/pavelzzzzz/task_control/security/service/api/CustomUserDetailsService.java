package com.pavelzzzzz.task_control.security.service.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.hibernate.entity.User;
import com.pavelzzzzz.task_control.rest.dto.UserDto;
import com.pavelzzzzz.task_control.security.service.CustomUserDetails;
import com.pavelzzzzz.task_control.service.api.EntityTransform;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService, EntityTransform<UserDto, CustomUserDetails, User> {

    UserDto create(CustomUserDetails customUserDetails) throws PocException;

    UserDto findByUsernameAndCheckPassword(String username, String password);
}
