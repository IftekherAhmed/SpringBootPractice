package com.blog.service;

import com.blog.entity.Category;
import com.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // Method to get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Method to create a new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Method to get a category by its ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Method to update an existing category
    public void updateCategory(Long id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());
            categoryRepository.save(updatedCategory);
        }
    }

    // Method to delete a category by its ID
    public void deleteCategory(Long id) {
        if (id == 1) {
            throw new IllegalArgumentException("Category with ID 1 cannot be deleted.");
        }
        categoryRepository.deleteById(id);
    }
}
