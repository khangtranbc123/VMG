package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@SuppressWarnings("serial")
@Entity
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String authorName;
    @JsonIgnore
    @OneToMany(mappedBy = "authors")
    private List<Blog> blogAuthor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Blog> getBlogAuthor() {
        return blogAuthor;
    }

    public void setBlogAuthor(List<Blog> blogAuthor) {
        this.blogAuthor = blogAuthor;
    }
}
