package com.example.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BasketProduct {

    private String productName;
    private String image;
    private int count;
    private int sum;
}
