package com.example.shoppingmall.dao;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class OrderDetailsDao {     // 주문 내역서 테이블

    private String orderDetailsId;

    private String customerId;

    private String receiver;

    private String phone;

    private String address;

    private String zipCode;

    private LocalDateTime orderDateTime;

    private String orderState;  // 주문 상태

    private int orderTotalPrice;

    private String payment;

    @Builder
    public OrderDetailsDao(String orderDetailsId, String customerId, String receiver, String phone,
                           String address, String zipCode, LocalDateTime orderDateTime, String orderState,
                           int orderTotalPrice, String payment) {
        this.orderDetailsId = orderDetailsId;
        this.customerId = customerId;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
        this.orderDateTime = orderDateTime;
        this.orderState = orderState;
        this.orderTotalPrice = orderTotalPrice;
        this.payment = payment;
    }
}
