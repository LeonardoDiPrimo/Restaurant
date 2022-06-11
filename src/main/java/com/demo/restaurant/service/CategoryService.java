package com.demo.restaurant.service;

import com.demo.restaurant.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

//The service methods are declared but not the implementation
@Service
public interface CategoryService {
    Category save(Category category);
    Category findById(Long id);
    List<Category> findAll();
    Category updateCategory (Category category);
}
