package com.blog.controller;


import com.blog.model.Blog;
import com.blog.model.BlogForm;
import com.blog.model.Category;
import com.blog.service.blog.BlogService;
import com.blog.service.blog.IBLogService;
import com.blog.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    public IBLogService blogService;

    @Autowired
    public ICategoryService categoryService;

    @ModelAttribute(name = "categoryList")
    private List<Category> categories() {
        return categoryService.findAll();
    }

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("")
    public String showList(Model model, @PageableDefault(value = 2) Pageable pageable) {
        Page<Blog> blogList = blogService.findAll(pageable);
        model.addAttribute("blogList", blogList);
        return "/list";
    }

    @GetMapping("/create-form")
    public String showCreateForm(Model model) {
        BlogForm blogForm = new BlogForm();
        model.addAttribute("blogForm", blogForm);
        return "/create";
    }

    @PostMapping("/create")
    public String create(BlogForm blogForm, Model model) {
        MultipartFile multipartFile = blogForm.getImg();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(blogForm.getImg().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Blog blog = new Blog(blogForm.getTitle(), blogForm.getBrief(), blogForm.getContent(), fileName, blogForm.getDate(), blogForm.getCategory());
        blogService.save(blog);
        model.addAttribute("blogForm", blogForm);
        model.addAttribute("message", "Create new product successfully!!");
        return "/create";
    }

    @GetMapping("/editForm/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id).get();
        BlogForm blogForm = new BlogForm(blog.getId(), blog.getTitle(), blog.getBrief(), blog.getContent(), null);
        model.addAttribute("blogForm", blogForm);
        return "/edit";
    }

    @PostMapping("/editForm/{id}")
    public String edit(@ModelAttribute BlogForm blogForm, Model model, @PathVariable Long id) {
        Blog blog1 = blogService.findById(id).get();
        model.addAttribute("blogForm", blogForm);
        MultipartFile image = blogForm.getImg();
        String fileName;
        Blog blog;
        if (image.getSize() != 0) {
            fileName = image.getOriginalFilename();
            try {
                FileCopyUtils.copy(image.getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            blog = new Blog(blogForm.getId(), blogForm.getTitle(), blogForm.getBrief(), blogForm.getContent(), fileName);
        } else {
            fileName = blog1.getImg();
            blog = new Blog(blogForm.getId(), blogForm.getTitle(), blogForm.getBrief(), blogForm.getContent(), fileName);
        }

        blogService.save(blog);
        return "/edit";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id).get();
        model.addAttribute("blog", blog);
        return "/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        blogService.remove(id);
//        List<Blog> blogList = ibLogService.findAll();
//        model.addAttribute("blogList",blogList);
        return "redirect:/blog";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam Optional<String> search) {
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Blog> blogList;
        if (search.isPresent()) {
            blogList = blogService.findAllByTitle(search.get());
        } else {
            blogList = blogService.findAll();
        }
        modelAndView.addObject("blogList", blogList);
        return modelAndView;
    }


    @GetMapping("/selectCategory")
    public ModelAndView selectCategory(@RequestParam Category category) {
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Blog> blogList = blogService.findByCategory(category);
        modelAndView.addObject("blogList", blogList);
        return modelAndView;
    }

    @PostMapping("/sortByDate")
    public ModelAndView sortByDate(Model model,@PageableDefault(value = 2) Pageable pageable, @RequestParam(name = "order") String order) {
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Blog> blogList = new ArrayList<>();
        if (order.equals("oldest")){
            blogList = blogService.findAllOrderByDate(pageable);
        } else {
            blogList = blogService.findAllOrderByDateDesc(pageable);
        }
        model.addAttribute("blogList",blogList);
        return modelAndView;
    }
}
