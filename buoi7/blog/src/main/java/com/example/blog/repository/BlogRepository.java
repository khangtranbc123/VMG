package com.example.blog.repository;

import com.example.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Query("SELECT DISTINCT b FROM Blog b JOIN b.categorys c JOIN b.authors a " +
            "WHERE c.name LIKE %?1% " +
            "OR a.authorName LIKE %?1%" +
            " OR b.title LIKE %?1% ")
    public List<Blog> finByText(String text);

}
