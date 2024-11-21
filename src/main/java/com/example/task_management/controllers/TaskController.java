package com.example.task_management.controllers;

import com.example.task_management.domains.task.Task;
import com.example.task_management.repositories.TaskRepository;
import com.example.task_management.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/task/")
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public TaskController(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    // create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(
        @RequestParam UUID userId,
        @RequestParam UUID categoryId,
        @RequestBody Task task
    ) {
        Task createdTask = taskService.createTask(userId, categoryId, task);
        return ResponseEntity.ok(createdTask);
    }

    // get user tasks
    @GetMapping
    void getTasks() {
        taskRepository.findAll();
    }

    // update a task
    @PutMapping
    void updateTask() {

    }

    // delete a task
    @DeleteMapping
    void deleteTask() {

    }
}
