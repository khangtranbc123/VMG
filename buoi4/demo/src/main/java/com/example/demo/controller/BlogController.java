package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Category;
import com.example.demo.entity.Cover;
import com.example.demo.form.BlogForm;
import com.example.demo.service.IBlogService;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.ICoverService;

import com.example.demo.service.impl.BlogServiceImpl;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BlogController {
    @Autowired private ICoverService iCoverService;
    @Autowired private IBlogService iBlogService;
    @Autowired private ICategoryService iCategoryService;
//    @Value("${upload.path}")
//    private String fileUpload;
    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping("/list")
    public List<Blog> finAll(){
        List<Blog> blogList = iBlogService.getAll();
        return blogList;
    }

//    @GetMapping("/index")
//    public ModelAndView getAll(@Valid Blog blog, Errors errors, Model model, @RequestParam("id")Optional<Integer> id){
//        List<Cover> covers = iCoverService.getAll();
//        List<Category> cates = iCategoryService.getAll();
//        ModelAndView modelAndView = new ModelAndView("/index");
//        modelAndView.addObject("covers", covers );
//        modelAndView.addObject("cates", cates );
//        modelAndView.addObject("message", "thanh cong" );
//        // model.addAttribute("blogs", blogs);
//        if(id.isPresent()) {
//            System.out.println(id.get());
//            modelAndView.addObject("blogs", iCategoryService.finById(id.get()).get().getBlogs());
//        }else {
//            List<Blog> blogs = iBlogService.getAll();
//            modelAndView.addObject("blogs", blogs );
//
//        }
//        return modelAndView;
//    }


    @GetMapping("/add")
    public ModelAndView add(Model model){
        ModelAndView modelAndView = new ModelAndView("/create");
        List<Category> category = iCategoryService.getAll();
        modelAndView.addObject("category", category);
        modelAndView.addObject("blog", new BlogForm());
        return  modelAndView;
    }
//    public String add(Blog blog)
//    {
//        return "create";
//    }

    @PostMapping("/create")
    public String createBlog( @Validated @ModelAttribute("blog") BlogForm blogForm,Errors errors, BindingResult result, Model model){
            if(errors.hasErrors()){
//                System.out.println(errors.getAllErrors());
                return "create";
            }
         Blog blog = new Blog.BlogBuilder(blogForm.getTitle()).content(blogForm.getContent()).build();
          blog.setCategories(blogForm.getCategory());
          iBlogService.save(blog);
          for (MultipartFile file: blogForm.getFiles()){
              try {
                   String fileName = file.getOriginalFilename();
                //   var is = file.getInputStream();
                   Files.copy(file.getInputStream(), Paths.get(this.fileUpload + fileName), StandardCopyOption.REPLACE_EXISTING);
                 // FileCopyUtils.copy(blog.getCover().getBytes(), new File(this.fileUpload + fileName));
                  Cover cover = new Cover(fileName, blog);
                  iCoverService.save(cover);
              } catch (IOException e){
                  e.printStackTrace();
              }
             }
          return "redirect:/index";
       // return new RedirectView("/blog");
    }




//        Blog blog1 = new Blog.BlogBuilder(blog.getTitle()).content(blog.getContent()).build();
//        MultipartFile multipartFile = blog.getCover();
//        String fileName = multipartFile.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(blog.getCover().getBytes(), new File(this.fileUpload + fileName));
//        }catch(IOException ioException) {
//            ioException.printStackTrace();
//        }
//        blog1.setCover(fileName);
//        blog1.setCategory(blog.getCategory());
//        iBlogService.save(blog1);
//        return new RedirectView("/blog");


//    public String create(@Validated Blog blog, BindingResult bindingResult, Model model){
//        if (bindingResult.hasErrors()) {
//            return "create";
//        }
//        iBlogService.save(blog);
//        return  "redirect:/index";
//    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteBlog(@PathVariable("id") Integer id, Blog blog){
        blog = iBlogService.finById(id).orElseThrow(() -> new IllegalArgumentException("Erorr controller"));
        iBlogService.delete(blog.getId());
        return new RedirectView("/blog");
    }
//    public String deleteBlog(@PathVariable("id") Integer id, Blog blog, Model model  ){
//        blog = iBlogService.finById(id).orElseThrow(() -> new IllegalArgumentException("loiii"));
//        iBlogService.delete(blog.getId());
//        return "redirect:/index";
//    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, Model model){
        ModelAndView modelAndView = new ModelAndView("/update");
        Blog blog = iBlogService.finById(id).orElseThrow(() -> new IllegalArgumentException("loi controller"));
        modelAndView.addObject("blog", blog );
        return modelAndView;
    }

//    @GetMapping("/find")
//    @ResponseBody
//    public String find(@RequestParam(name = "id") Integer id){
//        System.out.println(id);
//        return "id =" + id;
//    }
////    public String editBlog(@PathVariable("id") Integer id, Model model){
//        Blog blog = iBlogService.finById(id).orElseThrow(() -> new IllegalArgumentException("loiii"));
//        model.addAttribute("blog", blog);
//        return "update";
//    }

    @PostMapping("/updateBlog/{id}")
    public RedirectView updateBlog(@PathVariable("id") Integer id, @Validated Blog blog){
        iBlogService.save(blog);
        return new RedirectView("/blog");
    }
//    public String updateBlog(@PathVariable("id") Integer id, Model model, @Validated Blog blog){
//        iBlogService.save(blog);
//        return "redirect:/index";
//    }
}
