package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.service.BlogService;

@Controller
public class FrontendController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String home() {
        return "frontend/home";
    }

    @GetMapping("/blog")
    public String blogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "frontend/blog";
    }
}
