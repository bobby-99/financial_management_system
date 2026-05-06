package com.ichotu.finanacialmanagement.controller;


import com.ichotu.finanacialmanagement.config.SecurityConfig;
import com.ichotu.finanacialmanagement.entity.Category;
import com.ichotu.finanacialmanagement.security.CategoryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category create(@RequestParam String name){
        UUID userId = (UUID) SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();

        return categoryService.create(name, userId);
    }

    @GetMapping
    public List<Category> getAll(){
        UUID userId = (UUID) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return categoryService.getUserCategories(userId);
    }

}
