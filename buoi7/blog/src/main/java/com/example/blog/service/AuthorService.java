package com.example.blog.service;

import com.example.blog.model.Author;
import com.example.blog.model.Blog;
import com.example.blog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired private AuthorRepository authorRepository;

    public List<Author> getList() {
        return authorRepository.findAll();
    }

    public Author getById(Integer id) {
        return authorRepository.findById(id).get();
    }

    public void saveOrUpdate(Author author) {
        authorRepository.save(author);
    }

    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }
}
