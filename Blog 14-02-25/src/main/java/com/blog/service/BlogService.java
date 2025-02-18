package com.blog.service;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.entity.Category;
import com.blog.repository.BlogRepository;
import com.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    public Blog createBlog(Blog blog, User user, Set<Long> categoryIds) {
        blog.setUser(user);
        blog.setCreatedAt(LocalDateTime.now());
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
        blog.setCategories(categories);
        return blogRepository.save(blog);
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public void updateBlog(Long id, Blog blog, Set<Long> categoryIds) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            Blog updatedBlog = existingBlog.get();
            updatedBlog.setTitle(blog.getTitle());
            updatedBlog.setContent(blog.getContent());
            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
            updatedBlog.setCategories(categories);
            blogRepository.save(updatedBlog);
        }
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
