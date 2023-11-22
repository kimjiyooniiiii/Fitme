package com.example.shoppingmall.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class OrderedItemsDao {     // 주문 상품 상세테이블

    private Long orderedItemsId;

    private String orderDetailsId;      // 주문 내역서

    private Long productId;                // 상품 아이디

    private String productName;

    private String options;         // {색상 : 개수}

    private int productPrice;

}
