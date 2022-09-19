package com.example.blog.service.impl;

import com.example.blog.model.ERole;
import com.example.blog.model.Role;
import com.example.blog.repository.RoleRepository;
import com.example.blog.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
