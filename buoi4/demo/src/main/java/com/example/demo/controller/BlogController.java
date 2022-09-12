package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Category;
import com.example.demo.form.BlogForm;
import com.example.demo.service.IBlogService;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired private IBlogService iBlogService;

    @Autowired private ICategoryService iCategoryService;
    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping()
    public ModelAndView get(Model model){
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Blog> blogs = iBlogService.getAll();
        List<Category> cates = iCategoryService.getAll();
        modelAndView.addObject("blogs", blogs );
        modelAndView.addObject("cates", cates );
        modelAndView.addObject("message", "thanh cong" );
        // model.addAttribute("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("/create");
        List<Category> cates = iCategoryService.getAll();
        modelAndView.addObject("cates", cates);
        modelAndView.addObject("blog", new BlogForm());
        return  modelAndView;
    }
//    public String add(Blog blog)
//    {
//        return "create";
//    }

    @PostMapping("/create")
    public RedirectView createBlog(@ModelAttribute BlogForm blog){
        Blog blog1 = new Blog.BlogBuilder(blog.getTitle()).content(blog.getContent()).category(blog.getCategory()).build();

        MultipartFile multipartFile = blog.getCover();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(blog.getCover().getBytes(), new File(this.fileUpload + fileName));
        }catch(IOException ioException) {
            ioException.printStackTrace();
        }
//        Blog blog1 = new Blog(blog.getTitle(),fileName, blog.getContent(), blog.getCategory());
        System.out.println("check " + blog.getCategory());
        blog1.setCover(fileName);
        iBlogService.save(blog1);
        return new RedirectView("/blog");
    }

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
//    public String editBlog(@PathVariable("id") Integer id, Model model){
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
