package com.example.demo.service;



import com.example.demo.entity.Cover;

import java.util.List;
import java.util.Optional;

public interface ICoverService {
    List<Cover> getAll();
    Cover save(Cover cover);
    void delete(Integer id);
    Optional<Cover> finById(Integer id);
}
