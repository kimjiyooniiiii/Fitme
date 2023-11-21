package com.example.shoppingmall.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "product")
@Getter
@Builder
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private String shortDescrip;

    private int price;

    private int stock;

    private LocalDateTime createDate;
}
