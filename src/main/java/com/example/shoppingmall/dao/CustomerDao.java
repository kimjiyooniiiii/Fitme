package com.example.shoppingmall.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter @Setter
public class CustomerDao {

    private String customerId;

    private String password;

    private String name;

    private String phone;

    private List<OrderDetailsDao> orderDetails;

}
