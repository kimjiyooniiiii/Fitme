package com.example.shoppingmall.dao;

import com.example.shoppingmall.enumFile.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 회원의 주문 내역을 DB에서 SELECT해 오는 결과
@Getter @NoArgsConstructor
public class GetDetailsAndItems {

    private String orderDetailsId;  // 주문 번호

    private String receiver;

    private String phone;

    private String address;

    private String zipCode;

    private LocalDateTime orderDateTime;

    private OrderState orderState;  // 주문 상태

    private int orderTotalPrice;     // 주문 전체 가격

    private String payment;

    private Long productId;          // 상품 아이디

    private String options;         // {색상 : 개수}

    private int productPrice;       // 상품 당 총 가격

    private String productName;

    private String mainImageList;   // 상품 이미지

    @Builder
    public GetDetailsAndItems(String orderDetailsId, String receiver, String phone, String address, String zipCode, LocalDateTime orderDateTime, OrderState orderState, int orderTotalPrice, String payment, Long productId, String options, int productPrice, String productName, String mainImageList) {
        this.orderDetailsId = orderDetailsId;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
        this.orderDateTime = orderDateTime;
        this.orderState = orderState;
        this.orderTotalPrice = orderTotalPrice;
        this.payment = payment;
        this.productId = productId;
        this.options = options;
        this.productPrice = productPrice;
        this.productName = productName;
        this.mainImageList = mainImageList;
    }
}
