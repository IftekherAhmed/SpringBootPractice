package com.blog.service;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    public Blog createBlog(Blog blog, User user) {
        blog.setUser(user);
        blog.setCreatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }
}
