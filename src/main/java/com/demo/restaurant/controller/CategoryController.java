package com.demo.restaurant.controller;

import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.Category;
import com.demo.restaurant.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

//It is added to the default route for all controllers within the class
@RequestMapping("category/")
public class CategoryController {

    //Create an instance of the class in which the service logic is located
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    @Operation(summary = "Listar todas las categorias")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar una categoria por ID")
    public Category getCategory(@PathVariable(value = "id") Long id) {
        return categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @PostMapping()
    @Operation(summary = "Crear categoria")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping()
    @Operation(summary = "Actualizar categoria")
    public Category updateCategory(@RequestBody Category category) {
       return categoryService.updateCategory(category);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar categoria por ID")
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
