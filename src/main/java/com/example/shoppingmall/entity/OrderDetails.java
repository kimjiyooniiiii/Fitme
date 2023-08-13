package com.example.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
@Entity(name = "orderdetails")
public class OrderDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "orderlist_id")
    private OrderList orderList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Long productPrice;

    private int productCount;
}
