package com.example.demo.form;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BlogForm {
    private Integer id;
    private String title;
    private MultipartFile cover;
    private String content;
    private Category category;


    public BlogForm(){
    }
    public BlogForm(BlogFormBuilder blogFormBuilder) {
        this.title = blogFormBuilder.title;
        this.content = blogFormBuilder.content;
        this.cover = blogFormBuilder.cover;
        this.category = blogFormBuilder.category;
    }

    public static class BlogFormBuilder{
        private final String title;
        private String content;
        private MultipartFile cover;
        private Category category;

        public BlogFormBuilder(String title){
            this.title = title;
        }
        public BlogFormBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BlogFormBuilder cover(MultipartFile cover) {
            this.cover = cover;
            return this;
        }
        public BlogFormBuilder cate(Category category){
            this.category = category;
            return this;
        }

        public BlogForm build() {
            return new BlogForm(this);
        }
    }


}
