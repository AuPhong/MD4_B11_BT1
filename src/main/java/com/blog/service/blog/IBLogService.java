package com.blog.service.blog;

import com.blog.model.Blog;
import com.blog.model.Category;
import com.blog.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IBLogService extends IGenericService<Blog> {
    Page<Blog> findAll(Pageable pageable);
    List<Blog> findAllByTitle(String title);
    List<Blog> findByCategory(Category category);
    List<Blog> findAllOrderByDate(Pageable pageable);
    List<Blog> findAllOrderByDateDesc(Pageable pageable);
    List<Blog> findAllByCategory_Id(Long id);

}
