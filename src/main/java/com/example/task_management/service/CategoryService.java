package com.example.task_management.service;

import com.example.task_management.domains.category.Category;
import com.example.task_management.domains.category.CategoryDto;
import com.example.task_management.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.name());

        categoryRepository.save(category);

        return category;
    }

    public void deleteCategory(UUID uuid) {
        System.out.println("UUID: "+uuid);
        categoryRepository.deleteById(uuid);
    }
}
