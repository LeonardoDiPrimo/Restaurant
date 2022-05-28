package com.demo.restaurant.controller;

import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.Product;
import com.demo.restaurant.service.CategoryService;
import com.demo.restaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}
