package com.product_crud.service;

import com.product_crud.entity.Category;
import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void deleteCategory(Long id);
}
