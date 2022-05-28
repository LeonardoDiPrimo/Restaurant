package com.demo.restaurant.service;

import com.demo.restaurant.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    Category save(Category category);
    void deleteById(Long id);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    Category updateCategory (Category category);
}
