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
    private final CategoryService categoryService;

    @GetMapping
    public String categoryPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "backend/category/category-list";
    }

    @GetMapping("/create")
    public String createCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "backend/category/create-category";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category category, Model model) {
        categoryService.createCategory(category);
        model.addAttribute("success", "Category created successfully.");
        return "redirect:/backend/category?success=Category created successfully.";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryPage(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id).orElse(new Category()));
        return "backend/category/edit-category";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category, Model model) {
        categoryService.updateCategory(id, category);
        model.addAttribute("success", "Category updated successfully.");
        return "redirect:/backend/category?success=Category updated successfully.";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        try {
            categoryService.deleteCategory(id);
            model.addAttribute("success", "Category deleted successfully.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/backend/category";
    }
}
