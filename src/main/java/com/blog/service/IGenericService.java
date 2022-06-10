package com.blog.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    void remove(Long id);
    T save(T t);
}
