package com.ichotu.finanacialmanagement.security;

import com.ichotu.finanacialmanagement.entity.Category;
import com.ichotu.finanacialmanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(String name, UUID userId){
        Category c = new Category();

        c.setId(UUID.randomUUID());
        c.setName(name);
        c.setUserId(userId);

        return categoryRepository.save(c);
    }

    public List<Category> getUserCategories(UUID userId) {
        return categoryRepository.findByUserId(userId);
    }
}
