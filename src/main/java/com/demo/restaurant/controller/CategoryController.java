package com.demo.restaurant.controller;

import com.demo.restaurant.model.Category;
import com.demo.restaurant.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//It is added to the default route for all controllers within the class
@RequestMapping("category/")
@CrossOrigin
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
        return categoryService.findById(id);
    }

    @PostMapping()
    @Operation(summary = "Crear categoria")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping()
    @Operation(summary = "Actualizar categoria")
    public Category updateCategory(@Valid @RequestBody Category category) {
       return categoryService.updateCategory(category);
    }
}
