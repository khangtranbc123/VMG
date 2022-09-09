package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.service.IBlogService;
import com.example.demo.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService iBlogService;

    @GetMapping("/index")
    public String get(Model model){
        List<Blog> blogs = iBlogService.getAll();
        model.addAttribute("blogs", blogs);
        return "index";
    }

    @GetMapping("/add")
    public String add(Blog blog)
    {
        return "create";
    }

    @PostMapping("/createBlog")
    public String create(@Validated Blog blog, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "create";
        }
        iBlogService.save(blog);
        return  "redirect:/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") Integer id, Blog blog, Model model  ){
        blog = iBlogService.finById(id).orElseThrow(() -> new IllegalArgumentException("loiii"));
        iBlogService.delete(blog.getId());
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String editBlog(@PathVariable("id") Integer id, Model model){
        Blog blog = iBlogService.finById(id).orElseThrow(() -> new IllegalArgumentException("loiii"));
        model.addAttribute("blog", blog);
        return "update";
    }

    @PostMapping("/updateBlog/{id}")
    public String updateBlog(@PathVariable("id") Integer id, Model model, @Validated Blog blog){
        iBlogService.save(blog);
        return "redirect:/index";
    }
}
