package com.demo.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//Entity that corresponds to the table generated in the database
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private double price;

    @NotNull
    private int stock;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    private Boolean deprecated;
}
