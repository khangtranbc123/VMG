package com.example.demo.controller;
import com.example.demo.entity.Category;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cate")
public class CategoryController {
    @Autowired private ICategoryService iCategoryService;

    @GetMapping()
    public ModelAndView indexx(Model model){
        ModelAndView modelAndView = new ModelAndView("/category/index123");
        List<Category> cates = iCategoryService.getAll();
        modelAndView.addObject("cates", cates);
        return modelAndView;
    }
    @GetMapping("/create")
    public String getForm(Category category){
        return "category/create";
    }
    @PostMapping("/createCate")
    public String create(@Validated Category category, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "category/create";
        }
        iCategoryService.save(category);
        return  "redirect:/blog";
    }

    @GetMapping("/edit/{id}")
    public String editCate(@PathVariable("id") Integer id, Model model){
        Category category = iCategoryService.finById(id).orElseThrow(() -> new IllegalArgumentException("loiii"));
        model.addAttribute("category", category);
        return "category/update";
    }

    @PostMapping("/updateCate/{id}")
    public String updateBlog(@PathVariable("id") Integer id, Model model, @Validated Category category){
        iCategoryService.save(category);
        return "redirect:/cate";
    }
    @GetMapping("/delete/{id}")
    public String deleteCate(@PathVariable("id") Integer id, Category category, Model model  ){
        category = iCategoryService.finById(id).orElseThrow(() -> new IllegalArgumentException("loiii"));
        iCategoryService.delete(category.getId());
        return "redirect:/cate";
    }

}
