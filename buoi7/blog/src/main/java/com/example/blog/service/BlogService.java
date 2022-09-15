package com.example.blog.service;

import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired private BlogRepository blogRepository;

    public List<Blog> getList() {
        return blogRepository.findAll();
    }
    public List<Blog> findText(String text) {
        return blogRepository.finByText(text);
    }
    public Blog getById(Integer id) {
        return blogRepository.findById(id).get();
    }

    public void saveOrUpdate(Blog blog) {
        blogRepository.save(blog);
    }

    public void delete(Integer id) {
        blogRepository.deleteById(id);
    }
}
