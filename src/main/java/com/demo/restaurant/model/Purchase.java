package com.demo.restaurant.model;

import com.demo.restaurant.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long userId;

    @NonNull
    private double price;

    @NonNull
    private LocalDate dateOfPurchase;

    @NonNull
    private State state;
}
