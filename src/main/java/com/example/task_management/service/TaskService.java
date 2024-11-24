package com.example.task_management.service;

import com.example.task_management.domains.category.Category;
import com.example.task_management.domains.task.Task;
import com.example.task_management.domains.user.User;
import com.example.task_management.repositories.CategoryRepository;
import com.example.task_management.repositories.TaskRepository;
import com.example.task_management.repositories.UserRepository;
import com.example.task_management.utils.exceptions.TaskNotFoundException;
import com.example.task_management.utils.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Task createTask(UUID userId, UUID categoryId, Task task) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        task.setUser(user);
        task.setCategory(category);

        taskRepository.save(task);
        return task;
    }

    public List<Task> getAllTasks(UUID uuid) {
        return taskRepository.findByUserUuid(uuid);
    }

    public void deleteTask(UUID taskUuid, UUID userUuid) {

        Task task = taskRepository.findById(taskUuid)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!task.getUser().getUuid().equals(userUuid)) {
            throw new UnauthorizedException("You are not authorized to delete this task");
        }
        taskRepository.deleteById(taskUuid);
    }
}
