package com.example.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductFile {

    private Long productId;

    private String name;

    private String shortDescrip;

    private int price;

    private List<String> color;

    private List<String> fitSize;

    private List<String> mainImageList;

    private List<String> otherImageList;
}
