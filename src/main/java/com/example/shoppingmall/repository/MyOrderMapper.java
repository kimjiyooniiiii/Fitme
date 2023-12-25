package com.example.shoppingmall.repository;

import com.example.shoppingmall.dao.OrderDetailsDao;
import com.example.shoppingmall.dao.OrderedItemsDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyOrderMapper {

    // 회원의 주문 내역 불러오기
    List<OrderDetailsDao> findOrderByCustomerId(String customerId);

    List<OrderedItemsDao> find();
}
