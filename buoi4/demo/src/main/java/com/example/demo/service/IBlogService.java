package com.example.demo.service;

import com.example.demo.entity.Blog;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    List<Blog> getAll();
    Blog save(Blog blog);
    void delete(Integer id);
    Optional<Blog> finById(Integer id);
}
