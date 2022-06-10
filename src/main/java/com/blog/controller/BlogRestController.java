package com.blog.controller;

import com.blog.model.Blog;
import com.blog.model.BlogForm;
import com.blog.service.blog.IBLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BlogRestController {

    @Autowired
    IBLogService ibLogService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("/showList")
    public ResponseEntity<Iterable<Blog>> showList() {
        List<Blog> blogList = ibLogService.findAll();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        ibLogService.save(blog);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(@PathVariable Long id) {
        Optional<Blog> blog = ibLogService.findById(id);
        if (!blog.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> blogOptional = ibLogService.findById(id);
        if (blogOptional.isPresent()) {
            blog.setId(blogOptional.get().getId());
            return new ResponseEntity<>(ibLogService.save(blog),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id){
        Optional<Blog> blogOptional = ibLogService.findById(id);
        if (!blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ibLogService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/createWithImg")
    public ResponseEntity<?> createWithImg(@ModelAttribute BlogForm blogForm){
        MultipartFile multipartFile = blogForm.getImg();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(blogForm.getImg().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Blog blog = new Blog(blogForm.getTitle(), blogForm.getBrief(), blogForm.getContent(), fileName, blogForm.getDate(), blogForm.getCategory());
        ibLogService.save(blog);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

@GetMapping("/findByCategory")
    public ResponseEntity<?> findByCategory(@RequestParam Long id){
        List<Blog> blogList = ibLogService.findAllByCategory_Id(id);
        return new ResponseEntity<>(blogList,HttpStatus.OK);
}
}
