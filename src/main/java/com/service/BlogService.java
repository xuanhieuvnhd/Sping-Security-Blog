package com.service;

import com.model.Blog;
import com.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);

    void save(Blog blog);

    void remove(Long e);

    Blog findById(Long id);

    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
}
