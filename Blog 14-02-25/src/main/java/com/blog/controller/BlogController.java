package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.blog.repository.CategoryRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/backend")
public class BlogController {
    @Autowired
    private BlogService blogService; // Inject BlogService

    @Autowired
    private UserService userService; // Inject UserService

    @Autowired
    private CategoryRepository categoryRepository; // Inject CategoryRepository

    @GetMapping("/blog")
    public String blogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs()); // Add all blogs to the model
        return "backend/blog/blog-list"; // Return blog list page
    }

    @GetMapping("/blog/create")
    public String createBlogPage(Model model) {
        model.addAttribute("blog", new Blog()); // Add new blog to the model
        model.addAttribute("categories", categoryRepository.findAll()); // Add all categories to the model
        return "backend/blog/create-blog"; // Return create blog page
    }

    @PostMapping("/blog/create")
    public String createBlog(@ModelAttribute Blog blog, @RequestParam Set<Long> categoryIds, @AuthenticationPrincipal UserDetails auth_user, Model model) {
        User user = userService.findByUsername(auth_user.getUsername()); // Find user by username
        if (user == null) {
            model.addAttribute("error", "User must be logged in to create a blog"); // Add error message to the model
            return "backend/blog/create-blog"; // Return create blog page
        }
        blogService.createBlog(blog, user, categoryIds); // Create new blog
        model.addAttribute("success", "Blog created successfully."); // Add success message to the model
        return "redirect:/backend/blog?success=Blog created successfully."; // Redirect to blog list page with success message
    }

    @GetMapping("/blog/view/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id).orElse(new Blog())); // Add blog to the model
        return "backend/blog/view-blog"; // Return view blog page
    }

    @GetMapping("/blog/edit/{id}")
    public String editBlogPage(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id).orElse(new Blog())); // Add blog to the model
        model.addAttribute("categories", categoryRepository.findAll()); // Add all categories to the model
        return "backend/blog/edit-blog"; // Return edit blog page
    }

    @PostMapping("/blog/update/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog, @RequestParam Set<Long> categoryIds, Model model) {
        blogService.updateBlog(id, blog, categoryIds); // Update blog
        model.addAttribute("success", "Blog updated successfully."); // Add success message to the model
        return "redirect:/backend/blog?success=Blog updated successfully."; // Redirect to blog list page with success message
    }

    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Long id, Model model) {
        blogService.deleteBlog(id); // Delete blog
        model.addAttribute("success", "Blog deleted successfully."); // Add success message to the model
        return "redirect:/backend/blog?success=Blog deleted successfully."; // Redirect to blog list page with success message
    }
}