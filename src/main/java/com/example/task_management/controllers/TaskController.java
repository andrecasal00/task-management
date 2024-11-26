package com.example.task_management.controllers;

import com.example.task_management.domains.task.Task;
import com.example.task_management.repositories.TaskRepository;
import com.example.task_management.service.TaskService;
import com.example.task_management.utils.exceptions.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task/")
public class TaskController {
    private final TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
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
    @GetMapping("{user_uuid}")
    ResponseEntity<List<Task>> getTasks(@PathVariable("user_uuid") UUID userUUid) {
        return ResponseEntity.ok(taskService.getAllTasks(userUUid));
    }

    // update a task
    @PutMapping("{task_uuid}")
    ResponseEntity<Task> updateTask(@RequestBody Task updatedTask, @PathVariable("task_uuid") UUID taskUuid, @RequestParam("userUuid") UUID userUuid) {

        try {
            Task task = taskService.updateTask(taskUuid, userUuid, updatedTask);
            return ResponseEntity.ok(task);
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // delete a task
    @DeleteMapping("{task_uuid}")
    ResponseEntity<String> deleteTask(@PathVariable("task_uuid") UUID taskUuid, @RequestParam("userUuid") UUID userUuid) {
        try {
            taskService.deleteTask(taskUuid, userUuid);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }
}
