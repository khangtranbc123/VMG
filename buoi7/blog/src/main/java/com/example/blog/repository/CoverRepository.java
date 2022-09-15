package com.example.blog.repository;

import com.example.blog.model.Cover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverRepository extends JpaRepository<Cover, Integer> {
}
