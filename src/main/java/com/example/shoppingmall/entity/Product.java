package com.example.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
@Entity(name = "product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private int price;

    private int stock;
}
