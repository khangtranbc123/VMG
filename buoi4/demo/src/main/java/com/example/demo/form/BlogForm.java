package com.example.demo.form;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
public class BlogForm {
    private Integer id;
    private String title;
    private String content;
    private Set<CoverForm> coverForms;
    private Category category;

    private List<MultipartFile> files;

    public BlogForm(){
    }
    public BlogForm(BlogFormBuilder blogFormBuilder) {
        this.title = blogFormBuilder.title;
        this.content = blogFormBuilder.content;
        this.coverForms = blogFormBuilder.coverForms;
//        this.category = blogFormBuilder.category;
    }

    public static class BlogFormBuilder{
        private final String title;
        private String content;
        private Set<CoverForm> coverForms;
//        private Category category;

        public BlogFormBuilder(String title){
            this.title = title;
        }
        public BlogFormBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BlogFormBuilder cover(Set<CoverForm> cover) {
            this.coverForms = cover;
            return this;
        }
//        public BlogFormBuilder cate(Category category){
//            this.category = category;
//            return this;
//        }

        public BlogForm build() {
            return new BlogForm(this);
        }
    }


}
