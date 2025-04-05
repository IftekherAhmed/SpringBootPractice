package com.product_crud.service.impl;

import com.product_crud.entity.Comment;
import com.product_crud.entity.Product;
import com.product_crud.payload.CommentDto;
import com.product_crud.repository.CommentRepository;
import com.product_crud.repository.ProductRepository;
import com.product_crud.service.CommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    @Override
    public CommentDto addCommentToProduct(Long productId, CommentDto commentDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setProduct(product);

        Comment savedComment = commentRepository.save(comment);
        return mapToDto(savedComment);
    }

    @Override
    public List<CommentDto> getCommentsByProduct(Long productId) {
        return commentRepository.findAll()
                .stream()
                .filter(comment -> comment.getProduct().getId().equals(productId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentDto mapToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .productId(comment.getProduct().getId())
                .build();
    }
}

