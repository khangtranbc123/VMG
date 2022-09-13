package com.example.th2.service;

import com.example.th2.entity.City;

import java.util.List;

public interface ICityService {
    City findById(Long id);
    City save(City city);
    List<City> findAll();
}
