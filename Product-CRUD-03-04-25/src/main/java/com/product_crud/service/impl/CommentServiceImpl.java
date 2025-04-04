package com.product_crud.service.impl;

import com.product_crud.entity.Comment;
import com.product_crud.entity.Product;
import com.product_crud.repository.CommentRepository;
import com.product_crud.repository.ProductRepository;
import com.product_crud.service.CommentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ProductRepository productRepository) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Comment addCommentToProduct(Long productId, Comment comment) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        comment.setProduct(product);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByProduct(Long productId) {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

