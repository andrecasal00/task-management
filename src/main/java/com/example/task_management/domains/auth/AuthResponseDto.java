package com.example.task_management.domains.auth;

public record AuthResponseDto(
        String name,
        String token
) {
}
