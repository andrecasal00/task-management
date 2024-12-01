package com.example.task_management.domains.auth;

public record SignInDto(
        String email,
        String password
) {}