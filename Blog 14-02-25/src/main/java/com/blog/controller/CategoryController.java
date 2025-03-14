package com.blog.controller;

import com.blog.entity.Category;
import com.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/backend/category")
public class CategoryController {
    private final CategoryService categoryService; // Inject CategoryService

    // Category List
    @GetMapping
    public String categoryPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories()); // Add all categories to the model
        return "backend/category/category-list"; // Return category list page
    }

    // Category Creation View
    @GetMapping("/create")
    public String createCategoryPage(Model model) {
        model.addAttribute("category", new Category()); // Add new category to the model
        return "backend/category/create-category"; // Return create category page
    }

    // Category Creation
    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category category, Model model) {
        categoryService.createCategory(category); // Create new category
        model.addAttribute("success", "Category created successfully."); // Add success message to the model
        return "redirect:/backend/category?success=Category created successfully."; // Redirect to category list page with success message
    }

    // Category Edit View
    @GetMapping("/edit/{id}")
    public String editCategoryPage(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id).orElse(new Category())); // Add category to the model
        return "backend/category/edit-category"; // Return edit category page
    }

    // Category Update
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category, Model model) {
        categoryService.updateCategory(id, category); // Update category
        model.addAttribute("success", "Category updated successfully."); // Add success message to the model
        return "redirect:/backend/category?success=Category updated successfully."; // Redirect to category list page with success message
    }

    // Category Delete
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        try {
            categoryService.deleteCategory(id); // Delete category
            model.addAttribute("success", "Category deleted successfully."); // Add success message to the model
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // Add error message to the model
        }
        return "redirect:/backend/category"; // Redirect to category list page
    }
}
