package com.pavelzzzzz.task_control.rest.api;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.rest.dto.AuthRequest;
import com.pavelzzzzz.task_control.rest.dto.RegistrationRequest;
import com.pavelzzzzz.task_control.rest.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface AuthController {

    @PostMapping("/register")
    @Operation(summary = "registration of new users, return new UserDto")
    UserDto registerUser(RegistrationRequest registrationRequest) throws PocException;

    @PostMapping("/auth")
    @Operation(summary = "user authentication, return token")
    ResponseEntity<String> auth(AuthRequest request);
}
