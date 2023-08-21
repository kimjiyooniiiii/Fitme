package com.example.shoppingmall.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private Long productId;

    private String savePath;

    private String name;
}
