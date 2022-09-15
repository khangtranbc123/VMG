package com.example.blog.form;

import com.example.blog.model.Blog;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CoverForm {
    private Integer id;
    private MultipartFile name;
    private Blog blog;

    public CoverForm(){}
    public CoverForm(CoverFormBuilder coverFormBuilder) {
        this.name = coverFormBuilder.name;
        this.blog = coverFormBuilder.blog;
    }
    public static class CoverFormBuilder {
        private final MultipartFile name;
        private Blog blog;
        public CoverFormBuilder(MultipartFile file) {
            this.name = file;
        }
        public CoverFormBuilder blog(Blog blog) {
            this.blog = blog;
            return this;
        }
        public CoverForm build() {
            return new CoverForm(this);
        }
    }
}
