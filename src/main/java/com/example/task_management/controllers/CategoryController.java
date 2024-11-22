package com.example.task_management.controllers;

import com.example.task_management.domains.category.Category;
import com.example.task_management.domains.category.CategoryDto;
import com.example.task_management.repositories.CategoryRepository;
import com.example.task_management.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/category/")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // get all categories
    @GetMapping
    List<Category> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories()).getBody();
    }

    // create a category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody() CategoryDto dto) {
        Category category = categoryService.createCategory(dto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // delete category
    @DeleteMapping("{category_uuid}")
    public ResponseEntity<String> deleteCategory(@PathVariable() UUID category_uuid) {
        categoryService.deleteCategory(category_uuid);
        return ResponseEntity.ok("Category deleted");
    }
}
