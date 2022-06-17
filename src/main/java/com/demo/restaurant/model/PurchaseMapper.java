package com.demo.restaurant.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Transient;
import java.util.List;

@Data
public class PurchaseMapper {
    @NonNull
    private Long userId;

    @Transient
    @NonNull
    private List<ProductMapper> productList;
}
