package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.entity.Comment;
import com.blog.service.BlogService;
import com.blog.entity.Blog;

import java.util.Optional;

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

    @GetMapping("/blog/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.getBlogById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "frontend/blog-view";
        }
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestParam String name, @RequestParam String content) {
        Comment comment = new Comment();
        comment.setName(name);
        comment.setContent(content);
        blogService.addComment(id, comment);
        return "redirect:/blog/{id}";
    }

    @PostMapping(value = "/blog/{blogId}/comment/{commentId}/delete", params = "_method=delete")
    public String deleteComment(@PathVariable Long blogId, @PathVariable Long commentId) {
        blogService.deleteComment(blogId, commentId);
        return "redirect:/blog/{blogId}";
    }
}
