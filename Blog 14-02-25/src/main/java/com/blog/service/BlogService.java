package com.blog.service;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public void updateBlog(Long id, Blog blog) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            Blog updatedBlog = existingBlog.get();
            updatedBlog.setTitle(blog.getTitle());
            updatedBlog.setContent(blog.getContent());
            // ...update other fields as necessary...
            blogRepository.save(updatedBlog);
        }
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
