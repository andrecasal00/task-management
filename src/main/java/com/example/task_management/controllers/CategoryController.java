package com.example.task_management.controllers;

import com.example.task_management.domains.category.Category;
import com.example.task_management.domains.category.CategoryDto;
import com.example.task_management.repositories.CategoryRepository;
import com.example.task_management.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @DeleteMapping("{category_uuid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable() UUID category_uuid) {
        categoryService.deleteCategory(category_uuid);
        return ResponseEntity.noContent().build();
    }
}
