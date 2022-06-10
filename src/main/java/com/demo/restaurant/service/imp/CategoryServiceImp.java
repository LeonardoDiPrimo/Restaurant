package com.demo.restaurant.service.imp;

import com.demo.restaurant.exception.NonUniqueResultException;
import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.Category;
import com.demo.restaurant.repo.CategoryRepository;
import com.demo.restaurant.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    //Object instance to persist in the database
    private final CategoryRepository categoryRepository;

    //Class constructor
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
        if (optionalCategory.isPresent()) throw new NonUniqueResultException("Duplicate category name");
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category updateCategory) {
        Optional<Category> optionalCategory = categoryRepository.findById(updateCategory.getId());
        if (!optionalCategory.isPresent()) throw new ResourceNotFoundException("Category not found");

        Category category = optionalCategory.get();
        category.setName(updateCategory.getName());
        category.setDeprecated(updateCategory.getDeprecated());

        return categoryRepository.save(category);
    }
}
