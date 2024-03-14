package com.example.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class CustomerDto {

    private String customerId;

    private String name;

    private String phone;

}
