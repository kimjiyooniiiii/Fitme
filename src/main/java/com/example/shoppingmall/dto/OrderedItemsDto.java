package com.example.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItemsDto {
    private Long productId;
    private String productName;
    private Map<String, Integer> productOptions;
    private int productPrice;
}
