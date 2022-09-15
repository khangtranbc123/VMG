package com.example.blog.controller;

import com.example.blog.form.BlogForm;
import com.example.blog.model.Author;
import com.example.blog.model.Blog;
import com.example.blog.model.Category;
import com.example.blog.model.Cover;
import com.example.blog.service.AuthorService;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import com.example.blog.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BlogController {
    @Autowired private BlogService blogService;
    @Autowired private CategoryService categoryService;
    @Autowired private CoverService coverService;
    @Autowired private AuthorService authorService;
    @Value("${upload.path}")
    private String fileUpload;

//    public List<Blog> index(){
//        return blogService.getList();
//    }
    @GetMapping("/blog")
    public ResponseEntity<List<Blog>> getList() {
        return new ResponseEntity<List<Blog>>(blogService.getList(), HttpStatus.OK);
    }
    @GetMapping("/blog/find")
    public ResponseEntity<List<Blog>> getListText(@RequestParam("text") String text ) {
        return new ResponseEntity<List<Blog>>(blogService.findText(text), HttpStatus.OK);
    }
    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Integer id) {
        return new ResponseEntity<Blog>(blogService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/blog/save")
    public ResponseEntity<Void> saveOrUpdate(@RequestBody Blog blog) {
        blogService.saveOrUpdate(blog);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping("/blog/create")
    public ResponseEntity<Void> saveOrall(@ModelAttribute("blog")BlogForm blogForm, Blog blog) {
        blog = new Blog.BlogBuilder(blogForm.getTitle()).content(blogForm.getContent()).build();
        blog.setCategorys(blogForm.getCategory());
        blogService.saveOrUpdate(blog);
        for (MultipartFile file : blogForm.getFiles()){
            try {
                String fileName = file.getOriginalFilename();
                Files.copy(file.getInputStream(), Paths.get(this.fileUpload + fileName), StandardCopyOption.REPLACE_EXISTING);
                Cover cover = new Cover(fileName, blog);
                coverService.saveOrUpdate(cover);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/blog/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        blogService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
