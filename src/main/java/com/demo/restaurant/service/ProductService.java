package com.demo.restaurant.service;

import com.demo.restaurant.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
//The service methods are declared but not the implementation
@Service
public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Product updateProduct(Product product);
    List<Product> findProductByCategoryName(String categoryName);
}
