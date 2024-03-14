package com.example.shoppingmall.dao;

import lombok.*;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItemsDao {     // 주문 내역의 아이템들

    private Long orderedItemsId;

    private String orderDetailsId;      // 주문 내역서

    private Long productId;                // 상품 아이디

    private String productName;

    private String options;         // {색상사이즈 = 개수}

    private int productPrice;

    private String productImage;
}

//주문 아이템과 파일 테이블은 1:1 관계