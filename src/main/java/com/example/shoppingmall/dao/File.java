package com.example.shoppingmall.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private Long productId;

    private String savePath;

    private String name;

    private boolean isMain;
}
