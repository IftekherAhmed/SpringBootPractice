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
import com.blog.service.CommentService;
import com.blog.entity.Blog;

import java.util.Optional;

@Controller
public class FrontendController {
    @Autowired
    private BlogService blogService; // Inject BlogService

    @Autowired
    private CommentService commentService; // Inject CommentService

    @GetMapping("/")
    public String home() {
        return "frontend/home"; // Return home page
    }

    @GetMapping("/blog")
    public String blogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs()); // Add all blogs to the model
        return "frontend/blog"; // Return blog page
    }

    @GetMapping("/blog/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.getBlogById(id); // Get blog by ID
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get()); // Add blog to the model if present
            return "frontend/blog-view"; // Return blog view page
        }
        return "redirect:/blog"; // Redirect to blog page if blog not found
    }

    @PostMapping("/blog/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestParam String name, @RequestParam String content) {
        Comment comment = new Comment(); // Create new comment
        comment.setName(name); // Set comment name
        comment.setContent(content); // Set comment content
        commentService.addComment(id, comment); // Add comment to the blog
        return "redirect:/blog/{id}"; // Redirect to the blog view page
    }

    @PostMapping(value = "/blog/{blogId}/comment/{commentId}/delete", params = "_method=delete")
    public String deleteComment(@PathVariable Long blogId, @PathVariable Long commentId) {
        commentService.deleteComment(blogId, commentId); // Delete comment by ID
        return "redirect:/blog/{blogId}"; // Redirect to the blog view page
    }
}
