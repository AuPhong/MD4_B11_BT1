package com.blog.service.blog;

import com.blog.model.Blog;
import com.blog.model.Category;
import com.blog.repository.IBlogRepository;
//import com.blog.repository.blog.IBlogRepository;
import com.blog.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBLogService {

    @Autowired
    IBlogRepository blogRepository;

    @Autowired
    ICategoryRepository categoryRepository;



    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog;
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> findAllByCategory_Id(Long id) {
        return blogRepository.findAllByCategory_Id(id);
    }

    @Override
    public Blog save(Blog blog) {
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    public List<Blog> findAllByTitle(String title){
        return blogRepository.findAllByTitleContaining(title);
    }

    @Override
    public List<Blog> findByCategory(Category category) {
        return blogRepository.findByCategory(category);
    }

    public List<Blog> findAllOrderByDate(Pageable pageable){
        return blogRepository.findAllByOrderByDate(pageable);
    }

    public List<Blog> findAllOrderByDateDesc(Pageable pageable){
        return blogRepository.findAllByOrderByDateDesc(pageable);
    }

}
