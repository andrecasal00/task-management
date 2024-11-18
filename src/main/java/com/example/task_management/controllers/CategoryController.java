package com.example.task_management.controllers;

import com.example.task_management.domains.category.Category;
import com.example.task_management.domains.category.CategoryDto;
import com.example.task_management.repositories.CategoryRepository;
import com.example.task_management.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/category/")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {

        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    // get all categories
    @GetMapping
    List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // create a category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody() CategoryDto dto) {
        Category category = categoryService.createCategory(dto);
        return ResponseEntity.ok(category);
    }

    // delete category
    @DeleteMapping
    public void deleteCategory(String categoryId) {

    }
}
