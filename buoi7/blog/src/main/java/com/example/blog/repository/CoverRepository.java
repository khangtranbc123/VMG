package com.example.blog.repository;

import com.example.blog.model.Blog;
import com.example.blog.model.Cover;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverRepository extends JpaRepository<Cover, Integer> {
    public Cover findByBlog(Blog blog);
}
