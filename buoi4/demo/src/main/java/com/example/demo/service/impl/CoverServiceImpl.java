package com.example.demo.service.impl;

import com.example.demo.entity.Cover;
import com.example.demo.repository.CoverRepository;
import com.example.demo.service.ICoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CoverServiceImpl implements ICoverService {
    @Autowired private CoverRepository coverRepository;

    @Override
    public List<Cover> getAll() {
        return null;
    }

    @Override
    public Cover save(Cover cover) {
        return coverRepository.save(cover);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Optional<Cover> finById(Integer id) {
        return Optional.empty();
    }
}
