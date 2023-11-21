package com.example.shoppingmall.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CustomerDao {

    private String customerId;

    private String password;

    private String name;

    private String phone;

}
