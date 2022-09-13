package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
    public Cover(){}

    public Cover(CoverBuilder coverBuilder){
        this.name = coverBuilder.name;
        this.blog = coverBuilder.blog;
    }
    public static class CoverBuilder{
        private final String name;
        private Blog blog;

        public CoverBuilder(String name){
            this.name = name;
        }
        public CoverBuilder blog(Blog blog){
            this.blog = blog;
            return this;
        }
        public Cover build() {
            return new Cover(this);
        }
    }

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

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
