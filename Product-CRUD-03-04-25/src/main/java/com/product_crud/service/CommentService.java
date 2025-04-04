package com.product_crud.service;

import com.product_crud.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment addCommentToProduct(Long productId, Comment comment);
    List<Comment> getCommentsByProduct(Long productId);
    void deleteComment(Long id);
}

