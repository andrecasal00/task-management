package com.example.task_management.domains.task;

import java.util.Date;

public record TaskDto(
        String title,
        String description,
        TaskStatus status,
        Date dueDate
) {
}

