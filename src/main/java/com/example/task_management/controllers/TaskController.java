package com.example.task_management.controllers;

import com.example.task_management.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task/")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    // create a new task
    @PostMapping
    void createTask() {

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
