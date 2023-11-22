package com.example.shoppingmall.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter @Getter
@NoArgsConstructor
public class CustomerOrder {

    // 주문한 모든 내역서
    private List<GetDetailsAndItems> orderList;



}
