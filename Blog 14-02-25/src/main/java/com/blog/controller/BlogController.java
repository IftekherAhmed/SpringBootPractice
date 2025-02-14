package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/backend/blog")
    public String blogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "backend/blog/blog";
    }

    @GetMapping("/backend/blog/create")
    public String createBlogPage(Model model) {
        model.addAttribute("blog", new Blog());
        return "backend/blog/create-blog";
    }

    @PostMapping("/backend/blog/create")
    public String createBlog(@ModelAttribute Blog blog, @AuthenticationPrincipal User user) {
        blogService.createBlog(blog, user);
        return "redirect:/blog";
    }
}