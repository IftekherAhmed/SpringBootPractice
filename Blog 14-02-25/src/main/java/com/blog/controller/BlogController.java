package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/backend")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/blog")
    public String blogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "backend/blog/blog-list";
    }

    @GetMapping("/blog/create")
    public String createBlogPage(Model model) {
        model.addAttribute("blog", new Blog());
        return "backend/blog/create-blog";
    }

    @PostMapping("/blog/create")
    public String createBlog(@ModelAttribute Blog blog, @AuthenticationPrincipal UserDetails auth_user) {
        User user = userService.findByUsername(auth_user.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User must be logged in to create a blog");
        }
        blogService.createBlog(blog, user);
        return "redirect:/backend/blog";
    }

    @GetMapping("/blog/view/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id).orElse(new Blog()));
        return "backend/blog/view-blog";
    }

    @GetMapping("/blog/edit/{id}")
    public String editBlogPage(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id).orElse(new Blog()));
        return "backend/blog/edit-blog";
    }

    @PostMapping("/blog/update/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog) {
        blogService.updateBlog(id, blog);
        return "redirect:/backend/blog";
    }

    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/backend/blog";
    }
}