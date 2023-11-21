package com.example.shoppingmall.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
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
}
