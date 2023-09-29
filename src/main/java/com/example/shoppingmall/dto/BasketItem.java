package com.example.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
@Getter
public class BasketItem {

    private Long productId;
    private String productName;
    private int productPrice;
    private int count;
    private int totalPrice;
}
