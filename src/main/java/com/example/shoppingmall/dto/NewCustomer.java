package com.example.shoppingmall.dto;

import lombok.Builder;

@Builder
public class NewCustomer {

    private String customerId;

    private String password;

    private String name;

    private String phone;

    private String address;
}
