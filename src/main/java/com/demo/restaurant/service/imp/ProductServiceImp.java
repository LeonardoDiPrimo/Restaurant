package com.demo.restaurant.service.imp;

import com.demo.restaurant.exception.NonUniqueResultException;
import com.demo.restaurant.exception.PersistenceException;
import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.Product;
import com.demo.restaurant.repo.ProductRepository;
import com.demo.restaurant.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.demo.restaurant.enums.CategoryErrorMessage.CATEGORY_DEPRECATED;
import static com.demo.restaurant.enums.CategoryErrorMessage.CATEGORY_NOT_FOUND;
import static com.demo.restaurant.enums.ProductErrorMessage.PRODUCT_DUPLICATE;
import static java.lang.String.format;

@Service
public class ProductServiceImp implements ProductService {

    //Object instance to persist in the database
    private final ProductRepository productRepository;

    //Class constructor
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        Optional<Product> optionalProduct = productRepository.findByName(product.getName());
        if (optionalProduct.isPresent()) throw new NonUniqueResultException(format(PRODUCT_DUPLICATE.getMessage(), product.getName()));

        if (product.getCategory() == null) throw new ResourceNotFoundException(format(CATEGORY_NOT_FOUND.getMessage(), "NULL"));
        if (product.getCategory().getDeprecated()) throw new PersistenceException(CATEGORY_DEPRECATED.getMessage());

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
