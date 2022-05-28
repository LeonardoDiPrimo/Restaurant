package com.demo.restaurant.controller;

import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.Category;
import com.demo.restaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    public Category getCategory(@PathVariable(value = "id") Long id) {
        return categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @PostMapping()
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping()
    public Category updateCategory(@RequestBody Category category) {
       return categoryService.updateCategory(category);
    }

    @DeleteMapping("{id}")
    public Category deleteCategory(@PathVariable(value = "id") Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            categoryService.deleteById(category.getId());
            return category;
        }
        else throw new ResourceNotFoundException("Category not found");
    }
}
