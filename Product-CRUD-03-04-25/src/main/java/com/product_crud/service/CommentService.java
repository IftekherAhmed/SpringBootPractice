package com.product_crud.service;

import com.product_crud.payload.CommentDto;
import java.util.List;

public interface CommentService {
    CommentDto addCommentToProduct(Long productId, CommentDto commentDto);
    List<CommentDto> getCommentsByProduct(Long productId);
    void deleteComment(Long id);
}

