package com.demo.restaurant.service.imp;

import com.demo.restaurant.enums.PurchaseErrorMessage;
import com.demo.restaurant.enums.State;
import com.demo.restaurant.enums.UserErrorMessage;
import com.demo.restaurant.exception.PersistenceException;
import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.*;
import com.demo.restaurant.repo.PurchaseRepository;
import com.demo.restaurant.service.ProductService;
import com.demo.restaurant.service.PurchaseService;
import com.demo.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.restaurant.enums.CategoryErrorMessage.CATEGORY_DEPRECATED;
import static com.demo.restaurant.enums.ProductErrorMessage.PRODUCT_DEPRECATED;
import static com.demo.restaurant.enums.PurchaseErrorMessage.AMOUNT_ERROR;
import static com.demo.restaurant.enums.PurchaseErrorMessage.PURCHASE_NOT_FOUND;
import static java.lang.String.format;

@Service
public class PurchaseServiceImp implements PurchaseService {

    //Object instance to persist in the database
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase save(PurchaseMapper purchaseMapper) {
        //verified that the user exists
        User user = userService.findById(purchaseMapper.getUserId());
        if (user.getDeprecated()) throw new PersistenceException(UserErrorMessage.USER_DEPRECATED.getMessage());

        HashMap<Product, Integer> hashMap = new HashMap<>();
        double totalPrice = 0;

        for (ProductMapper productMapper : purchaseMapper.getProductList()) {
            Product product = productService.findById(productMapper.getProductId());
            if (product.getDeprecated()) throw new PersistenceException(format(PRODUCT_DEPRECATED.getMessage(), product.getName()));
            if (product.getCategory().getDeprecated()) throw new PersistenceException(format(CATEGORY_DEPRECATED.getMessage(), product.getCategory().getName()));
            if (productMapper.getAmount() <= 0) throw new PersistenceException(format(AMOUNT_ERROR.getMessage(), product.getName()));
            if (productMapper.getAmount() > product.getStock())
                throw new PersistenceException(format(PurchaseErrorMessage.STOCK_ERROR.getMessage(), product.getName(), product.getStock(), productMapper.getAmount()));

            int stock = product.getStock() - productMapper.getAmount();
            hashMap.put(product, stock);
            totalPrice = totalPrice + (product.getPrice() * productMapper.getAmount());
        }

        //Updated product stock
        hashMap.forEach((product, stock) -> {
            product.setStock(stock);
            productService.updateProduct(product);
        });

        Purchase purchase = new Purchase();
        
        purchase.setUserId(user.getId());
        purchase.setPrice(totalPrice);
        purchase.setDateOfPurchase(LocalDate.now());
        purchase.setState(State.PENDING);

        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> findPurchasesByUserId(int userId) {
        List<Purchase> purchases = findAll();
        return purchases.stream().filter(purchase -> purchase.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public Purchase updateStatus(Long purchaseId, State state) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
        if (!optionalPurchase.isPresent()) throw new ResourceNotFoundException(format(PURCHASE_NOT_FOUND.getMessage(), purchaseId));

        Purchase purchase = optionalPurchase.get();
        purchase.setState(state);
        purchaseRepository.save(purchase);
        return purchase;
    }
}
