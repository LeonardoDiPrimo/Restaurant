package com.demo.restaurant.service;

import com.demo.restaurant.model.Purchase;
import org.springframework.stereotype.Service;

import java.util.List;

//The service methods are declared but not the implementation
@Service
public interface PurchaseService {
    List<Purchase> findAll();
    Purchase save(Purchase purchase);
    List<Purchase> findPurchasesByUserId (int userId);

}
