package com.example.demo.form;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Category;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@Validated
public class BlogForm {
    private Integer id;
    @NotEmpty(message = "title can not be null")
   @Size(max = 20, min = 3, message = "3 to 20 characters")
    private String title;
    @NotEmpty(message = "content can not be null")
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
