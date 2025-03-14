package com.blog.service;

import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.repository.BlogRepository;
import com.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    // Method to add a comment to a blog
    public Comment addComment(Long blogId, Comment comment) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isPresent()) {
            comment.setBlog(blog.get());
            comment.setCreatedAt(new Date());
            return commentRepository.save(comment);
        }
        return null;
    }

    // Method to delete a comment from a blog
    public void deleteComment(Long blogId, Long commentId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isPresent()) {
            commentRepository.deleteById(commentId);
        }
    }
}
