package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@SuppressWarnings("serial")
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "categorys")
    private List<Blog> blogCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(List<Blog> blogCategory) {
        this.blogCategory = blogCategory;
    }
}
