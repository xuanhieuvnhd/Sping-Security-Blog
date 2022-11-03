package com.controller;

import com.model.Blog;
import com.model.Category;
import com.service.BlogService;
import com.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private  BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @RequestMapping("/userlist")
    public ModelAndView getAllBlog(@RequestParam("s")Optional<String> s, Pageable pageable) {

        Page<Blog> blogList;
        if(s.isPresent()){
            blogList = blogService.findAllByTitleContaining(s.get(),pageable);
        } else {
            blogList = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs",blogList);

        return modelAndView;
    }

    @GetMapping("/admincreate")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());

        return modelAndView;
    }

    @PostMapping("/adminsave-blog")
    public ModelAndView saveBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);

        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog",new Blog());
        modelAndView.addObject("message","Tao blog thanh cong!");
        return modelAndView;
    }

    @GetMapping("/userread/{id}")
    public ModelAndView readContent(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/read");
        modelAndView.addObject("blog",blog);

        return modelAndView;
    }

    @GetMapping("/adminedit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog",blog);

        return modelAndView;
    }

    @PostMapping("/adminupdate-blog")
    public ModelAndView updateBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);

        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message","Cap nhat blog thanh cong!");

        return modelAndView;
    }

    @GetMapping("/adminremove/{id}")
    public ModelAndView showRemoveForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/remove");
        modelAndView.addObject("blog",blog);

        return modelAndView;
    }

    @PostMapping("/admindelete-blog")
    public String removeBlog(@ModelAttribute ("blog") Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:list";
    }
}
