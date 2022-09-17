package com.example.blog.service;

import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired private BlogRepository blogRepository;

    public List<Blog> getList() {
        return blogRepository.findAll();
    }
    public Page<Blog> getByPage( int pageNumber, int maxNumber, Sort sort){
        Pageable pageable = PageRequest.of(pageNumber, maxNumber, sort);
        return blogRepository.findBlog(pageable);
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
