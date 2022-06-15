package com.demo.restaurant.controller;

import com.demo.restaurant.model.Purchase;
import com.demo.restaurant.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//It is added to the default route for all controllers within the class
@RequestMapping("purchase/")
@CrossOrigin
public class PurchaseController {

    //Create an instance of the class in which the service logic is located
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping()
    @Operation(summary = "Listar todos las compras realizadas")
    public List<Purchase> getPurchases() {
        return purchaseService.findAll();
    }

    @GetMapping("{userId}")
    @Operation(summary = "Listar todos las compras de un determinado usuario")
    public List<Purchase> findPurchasesByUserId(@PathVariable(value = "userId") int userId) {
        return purchaseService.findPurchasesByUserId(userId);
    }

    @PostMapping()
    @Operation(summary = "Crear Compra")
    public Purchase createPurchase(@Valid @RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

}
