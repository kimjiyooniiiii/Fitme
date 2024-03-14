package com.example.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductPreview {

    private Long productId;

    private List<String> mainImageList;

    private String name;

    private int price;
}
