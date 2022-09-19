package com.example.blog.service;

import com.example.blog.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
