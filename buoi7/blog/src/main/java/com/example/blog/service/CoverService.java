package com.example.blog.service;

import com.example.blog.model.Category;
import com.example.blog.model.Cover;
import com.example.blog.repository.CoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverService {
    @Autowired private CoverRepository coverRepository;

    public List<Cover> getList() {
        return coverRepository.findAll();
    }

    public Cover getById(Integer id) {
        return coverRepository.findById(id).get();
    }

    public void saveOrUpdate(Cover cover) {
        coverRepository.save(cover);
    }

    public void delete(Integer id) {
        coverRepository.deleteById(id);
    }
}
