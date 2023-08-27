package com.example.shoppingmall.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductFile {

    private Long productId;

    private String name;

    private String shortDescrip;

    private Long price;

    private String savePath;
}
