package com.product_crud.controller;

import com.product_crud.entity.Comment;
import com.product_crud.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{productId}")
    public Comment addComment(@PathVariable Long productId, @RequestBody Comment comment) {
        return commentService.addCommentToProduct(productId, comment);
    }

    @GetMapping("/{productId}")
    public List<Comment> getCommentsByProduct(@PathVariable Long productId) {
        return commentService.getCommentsByProduct(productId);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}

