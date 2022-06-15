package com.demo.restaurant.service.imp;

import com.demo.restaurant.model.Purchase;
import com.demo.restaurant.repo.PurchaseRepository;
import com.demo.restaurant.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImp implements PurchaseService {

    //Object instance to persist in the database
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> findPurchasesByUserId(int userId) {
        List<Purchase> purchases = findAll();
        return purchases.stream().filter(purchase -> purchase.getUserId() == userId).collect(Collectors.toList());
    }
}
