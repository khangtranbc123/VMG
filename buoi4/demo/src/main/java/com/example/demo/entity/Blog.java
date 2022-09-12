package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String cover;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "Categoryid")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog(BlogBuilder blogBuilder) {
        this.title = blogBuilder.title;
        this.cover = blogBuilder.cover;
        this.content = blogBuilder.content;
        this.category = blogBuilder.category;
    }
    public Blog(){

    }
    public static class BlogBuilder{
        private final String title;
        private String cover;
        private String content;
        private Category category;

        public BlogBuilder(String title){
            this.title = title;
        }
        public BlogBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BlogBuilder category(Category category){
            this.category = category;
            return this;
        }

        public BlogBuilder cover(String cover) {
            this.cover = cover;
            return this;
        }

        public Blog build() {
            return new Blog(this);
        }
    }
}
