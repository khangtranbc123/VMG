package com.example.blog.service;

import com.example.blog.model.ERole;
import com.example.blog.model.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(ERole name);
}
