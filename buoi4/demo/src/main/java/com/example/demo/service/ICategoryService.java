package com.example.demo.service;


import com.example.demo.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAll();
    void save(Category category);
    void delete(Integer id);
    Optional<Category> finById(Integer id);
}
