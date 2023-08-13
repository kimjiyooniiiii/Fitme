package com.example.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@Entity(name = "orderlist")
public class OrderList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "orderlist_id")
    private Long orderListId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @CreatedDate @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;

    private Long totalPrice;

    private String payment;
}
