package com.example.shoppingmall.repository;

import com.example.shoppingmall.dao.OrderDetailsDao;
import com.example.shoppingmall.dao.OrderedItemsDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

     //주문 내역 저장
    int insertOrderDetails(OrderDetailsDao orderDetailsDao);

    // 주문 상품 상세내역 저장
    int insertOrderedItems(List<OrderedItemsDao> orderedItemDaos);

    // 해당 고객의 주문내역 찾기
    OrderDetailsDao selectOrderByCustomerId(String customerId);
}
