package com.example.blog.controller;

import com.example.blog.model.Author;
import com.example.blog.model.Category;
import com.example.blog.utility.CommonConst;
import com.example.blog.form.BlogForm;
import com.example.blog.model.Blog;
import com.example.blog.model.Cover;
import com.example.blog.service.AuthorService;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import com.example.blog.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BlogController {
    @Autowired private BlogService blogService;
    @Autowired private CategoryService categoryService;
    @Autowired private CoverService coverService;
    @Autowired private AuthorService authorService;
    @Value("${upload.path}")
    private String fileUpload;
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') ")
    @GetMapping("/list1")
    public List<Blog> index(){
        return blogService.getList();
    }
    @GetMapping("/categorys")
    public List<Category> getCate(){
        return categoryService.getList();
    }
    @GetMapping("/authors")
    public List<Author> getAuthor(){
        return authorService.getList();
    }
//    @GetMapping("/blog")
//    public Page<Blog> getAll(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
//        Page<Blog> blogPage = blogService.getByPage( pageNumber, CommonConst.PAGE_SIZE);
//        return blogPage;
//    }
//  @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber)
@GetMapping("/list")
@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<Blog>> getList(@RequestParam(defaultValue = "0") int pageNumber
                                            ,@RequestParam( defaultValue = "5") int sizeNumber) {
        Pageable pageable = PageRequest.of(pageNumber, sizeNumber);
        return new ResponseEntity<Page<Blog>>(blogService.getByPage(pageable), HttpStatus.OK);
    }
    @GetMapping("/blog/find")
    public ResponseEntity<List<Blog>> getListText(@RequestParam(name="text") String text ) {
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
//    @ModelAttribute BlogForm blogForm,
@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
@PostMapping("/blog/create")
    public ResponseEntity<Void> saveOrUpdate( @ModelAttribute BlogForm blogForm) {
        Blog blog = new Blog.BlogBuilder(blogForm.getTitle()).content(blogForm.getContent()).build();
        blog.setAuthors(blogForm.getAuthors());
        blog.setCategorys(blogForm.getCategorys());
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
    @PutMapping("/blog/update/{id}")
    public ResponseEntity<Blog> updateBlogs(@PathVariable("id") Integer id, @RequestBody Blog blog){
        blogService.saveOrUpdate(blog);
        return new ResponseEntity<Blog>(HttpStatus.OK);
    }
    @DeleteMapping("/blog/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Blog blog = blogService.getById(id);
        Cover cover = coverService.getByBlog(blog);

        if (cover != null){
            Integer idC = cover.getId();
            coverService.delete(idC);
            blogService.delete(id);
        }else {
            blogService.delete(id);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
