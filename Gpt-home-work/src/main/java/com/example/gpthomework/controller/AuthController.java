package com.example.gpthomework.controller;

import com.example.gpthomework.model.LoginRequest;
import com.example.gpthomework.model.LoginResponse;
import com.example.gpthomework.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {

        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }
}
