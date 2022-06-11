package com.demo.restaurant.controller;

import com.demo.restaurant.model.Product;
import com.demo.restaurant.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

//It is added to the default route for all controllers within the class
@RequestMapping("product/")
public class ProductController {

    //Create an instance of the class in which the service logic is located
    @Autowired
    private ProductService productService;

    @GetMapping()
    @Operation(summary = "Listar todos los productos")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @PostMapping()
    @Operation(summary = "Crear Producto")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping()
    @Operation(summary = "Actualizar producto")
    public Product updateCategory(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("{categoryName}")
    @Operation(summary = "Filtrar productos no deprecados por nombre de categoria")
    public List<Product> findByCategoryName(@PathVariable(value = "categoryName") String categoryName) {
        return productService.findProductByCategoryName(categoryName);
    }
}
