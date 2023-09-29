package com.example.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BasketList {

    private List<BasketItem> basketList;
    private int totalPrice;
    /*private int totalCount;*/
    private int deliveryTip;
}
