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
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/blog")
    public String blogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "backend/blog/blog-list";
    }

    @GetMapping("/blog/create")
    public String createBlogPage(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryRepository.findAll());
        return "backend/blog/create-blog";
    }

    @PostMapping("/blog/create")
    public String createBlog(@ModelAttribute Blog blog, @RequestParam Set<Long> categoryIds, @AuthenticationPrincipal UserDetails auth_user, Model model) {
        User user = userService.findByUsername(auth_user.getUsername());
        if (user == null) {
            model.addAttribute("error", "User must be logged in to create a blog");
            return "backend/blog/create-blog";
        }
        blogService.createBlog(blog, user, categoryIds);
        model.addAttribute("success", "Blog created successfully.");
        return "redirect:/backend/blog?success=Blog created successfully.";
    }

    @GetMapping("/blog/view/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id).orElse(new Blog()));
        return "backend/blog/view-blog";
    }

    @GetMapping("/blog/edit/{id}")
    public String editBlogPage(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id).orElse(new Blog()));
        model.addAttribute("categories", categoryRepository.findAll());
        return "backend/blog/edit-blog";
    }

    @PostMapping("/blog/update/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog, @RequestParam Set<Long> categoryIds, Model model) {
        blogService.updateBlog(id, blog, categoryIds);
        model.addAttribute("success", "Blog updated successfully.");
        return "redirect:/backend/blog?success=Blog updated successfully.";
    }

    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Long id, Model model) {
        blogService.deleteBlog(id);
        model.addAttribute("success", "Blog deleted successfully.");
        return "redirect:/backend/blog?success=Blog deleted successfully.";
    }
}