package com.blog.repository;

import com.blog.model.Blog;
import com.blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IBlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findAllByTitleContaining(String title);
    List<Blog> findByCategory(Category category);
    List<Blog> findAllByOrderByDate(Pageable pageable);
    List<Blog> findAllByOrderByDateDesc(Pageable pageable);
    List<Blog> findAllByCategory_Id(Long id);
}
