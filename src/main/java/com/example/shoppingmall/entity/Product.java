package com.example.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "product")
@NoArgsConstructor
@Getter
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private int price;

    private int stock;

    private String mainImage;

    private LocalDateTime createDate;
}
