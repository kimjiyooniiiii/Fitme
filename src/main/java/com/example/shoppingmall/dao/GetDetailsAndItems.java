package com.example.shoppingmall.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// 회원의 주문 내역을 DB에서 SELECT해 오는 결과
@Getter @Setter @NoArgsConstructor
public class GetDetailsAndItems {

    private String orderDetailsId;  // 주문 번호

    private String receiver;

    private String phone;

    private String address;

    private String zipCode;

    private LocalDateTime orderDateTime;

    private String orderState;  // 주문 상태

    private int orderTotalPrice;     // 주문 전체 가격

    private String payment;

    private Long productId;          // 상품 아이디

    private String options;         // {색상 : 개수}

    private int productPrice;       // 상품 당 총 가격

    private String productName;

    private String mainImageList;   // 상품 이미지
}
