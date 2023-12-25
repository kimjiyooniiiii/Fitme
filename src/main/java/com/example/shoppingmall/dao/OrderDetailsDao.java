package com.example.shoppingmall.dao;

import com.example.shoppingmall.enumFile.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDetailsDao {     // 주문 내역서 테이블

    private String orderDetailsId;

    private String customerId;

    private String receiver;

    private String phone;

    private String address;

    private String zipCode;

    private LocalDateTime orderDateTime;

    private OrderState orderState;  // 주문 상태

    private int orderTotalPrice;

    private String payment;

    private List<OrderedItemsDao> itemsDaoList;     // 주문한 아이템 리스트

    @Builder
    public OrderDetailsDao(String orderDetailsId, String customerId, String receiver, String phone,
                           String address, String zipCode, LocalDateTime orderDateTime, OrderState orderState,
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
