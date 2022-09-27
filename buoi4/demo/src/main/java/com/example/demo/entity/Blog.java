package com.example.demo.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Validated
@SuppressWarnings("serial")
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotNull(message = "title can not be null")
    @Size(max = 20, min = 3, message = "3 to 20 characters")
    private String title;
    @Column
    @NotNull(message = "content can not be null")
    private String content;
    @ManyToOne
    @JoinColumn(name = "Categoryid")
    private Category categories;

    @OneToMany(mappedBy = "blog")
    private Set<Cover> covers;
    @Transient
    private List<MultipartFile> files;

    public Set<Cover> getCovers() {
        return covers;
    }

    public void setCovers(Set<Cover> covers) {
        this.covers = covers;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog(BlogBuilder blogBuilder) {
        this.title = blogBuilder.title;
        this.content = blogBuilder.content;
//        this.category = blogBuilder.category;
    }
    public Blog(){

    }
    public static class BlogBuilder{
        private final String title;
        private String content;
 //       private Category category;

        public BlogBuilder(String title){
            this.title = title;
        }
        public BlogBuilder content(String content) {
            this.content = content;
            return this;
        }

//        public BlogBuilder category(Category category){
//            this.category = category;
//            return this;
//        }

//        public BlogBuilder cover(String cover) {
//            this.cover = cover;
//            return this;
//        }

        public Blog build() {
            return new Blog(this);
        }
    }
}
