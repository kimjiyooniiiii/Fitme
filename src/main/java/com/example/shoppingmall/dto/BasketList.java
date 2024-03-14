package com.example.shoppingmall.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketList {

    private List<BasketItem> basketList;
    //private int totalPrice;
}
