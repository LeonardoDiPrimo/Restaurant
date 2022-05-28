package com.demo.restaurant.service.imp;

import com.demo.restaurant.exception.NonUniqueResultException;
import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.Product;
import com.demo.restaurant.repo.ProductRepository;
import com.demo.restaurant.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        Optional<Product> optionalProduct = productRepository.findByName(product.getName());
        if (optionalProduct.isPresent())
            throw new NonUniqueResultException("Duplicate product name");

        if (product.getCategory() == null || product.getCategory().getDeprecated())
            throw new ResourceNotFoundException("Category not found");

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
