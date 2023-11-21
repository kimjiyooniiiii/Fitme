package com.example.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {

    private String receiver;
    private String phone;
    private String address;
    private String zipCode;
    private String payment;
    private OrderedItemsDto[] orderedItemsDto;
    private int totalPrice;

}
