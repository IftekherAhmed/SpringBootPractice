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
    public String createCategory(@ModelAttribute Category category) {
        categoryService.createCategory(category);
        return "redirect:/backend/category";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryPage(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id).orElse(new Category()));
        return "backend/category/edit-category";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/backend/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/backend/category";
    }
}
