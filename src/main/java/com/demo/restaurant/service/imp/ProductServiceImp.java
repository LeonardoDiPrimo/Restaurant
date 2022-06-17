package com.demo.restaurant.service.imp;

import com.demo.restaurant.exception.NonUniqueResultException;
import com.demo.restaurant.exception.PersistenceException;
import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.Category;
import com.demo.restaurant.model.Product;
import com.demo.restaurant.repo.ProductRepository;
import com.demo.restaurant.service.CategoryService;
import com.demo.restaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.restaurant.enums.CategoryErrorMessage.CATEGORY_DEPRECATED;
import static com.demo.restaurant.enums.ProductErrorMessage.PRODUCT_DUPLICATE;
import static com.demo.restaurant.enums.ProductErrorMessage.PRODUCT_NOT_FOUND;
import static java.lang.String.format;

@Service
public class ProductServiceImp implements ProductService {

    //Object instance to persist in the database
    private final ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    //Class constructor
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        Optional<Product> optionalProduct = productRepository.findByName(product.getName());
        if (optionalProduct.isPresent()) throw new NonUniqueResultException(format(PRODUCT_DUPLICATE.getMessage(), product.getName()));

        Category category = categoryService.findById(product.getCategory().getId());
        if (category.getDeprecated()) throw new PersistenceException(format(CATEGORY_DEPRECATED.getMessage(), category.getName()));

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (!productOptional.isPresent()) throw new ResourceNotFoundException(format(PRODUCT_NOT_FOUND.getMessage(), product.getId()));
        return productRepository.save(product);
    }

    @Override
    public List<Product> findProductByCategoryName(String categoryName) {
        List<Product> productList = productRepository.findAll();

        return productList.stream().filter(product ->
                !product.getDeprecated() && product.getCategory().getName().toUpperCase().contains(categoryName.toUpperCase())).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) throw new ResourceNotFoundException(format(PRODUCT_NOT_FOUND.getMessage(), productId));
        return optionalProduct.get();
    }
}
