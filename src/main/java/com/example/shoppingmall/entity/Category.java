package com.example.shoppingmall.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
@Entity(name = "category")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    private int count;
}
