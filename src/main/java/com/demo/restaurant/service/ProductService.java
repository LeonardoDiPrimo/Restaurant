package com.demo.restaurant.service;

import com.demo.restaurant.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
}
