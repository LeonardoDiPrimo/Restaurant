package com.demo.restaurant.service;

import com.demo.restaurant.enums.State;
import com.demo.restaurant.model.Purchase;
import com.demo.restaurant.model.PurchaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

//The service methods are declared but not the implementation
@Service
public interface PurchaseService {
    List<Purchase> findAll();
    Purchase save(PurchaseMapper purchaseMapper);
    List<Purchase> findPurchasesByUserId (int userId);
    Purchase updateStatus(Long purchaseId, State state);

}
