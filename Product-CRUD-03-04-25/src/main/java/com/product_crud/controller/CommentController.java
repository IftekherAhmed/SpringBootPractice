package com.product_crud.controller;

import com.product_crud.payload.CommentDto;
import com.product_crud.service.CommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{productId}")
    public ResponseEntity<CommentDto> addComment(@PathVariable Long productId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.addCommentToProduct(productId, commentDto));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<CommentDto>> getCommentsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(commentService.getCommentsByProduct(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}

