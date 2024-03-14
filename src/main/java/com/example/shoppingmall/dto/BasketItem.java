package com.example.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {

    private Long productId;

    private String productName;

    // key : '색상사이즈', value : { 색상, 사이즈, 개수 }
    private HashMap<String, OptionDetails> productOptions;

    private String totalPrice;
}
