package com.example.shoppingmall.service;

import com.example.shoppingmall.dao.OrderDetailsDao;
import com.example.shoppingmall.dao.OrderedItemsDao;
import com.example.shoppingmall.dto.OrderDetailsDto;
import com.example.shoppingmall.dto.OrderedItemsDto;
import com.example.shoppingmall.enumFile.OrderState;
import com.example.shoppingmall.repository.CustomerMapper;
import com.example.shoppingmall.repository.OrderMapper;
import com.example.shoppingmall.security.CustomerUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private final CustomerMapper customerMapper;
    @Autowired
    private final OrderMapper orderMapper;

    public OrderService(CustomerMapper customerMapper, OrderMapper orderMapper) {
        this.customerMapper = customerMapper;
        this.orderMapper = orderMapper;
    }

    // 주문 로직
    public boolean orderComplete(OrderDetailsDto orderDetailsDto) {
        // 현재 로그인 된 사용자 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerUserDetails userDetails = (CustomerUserDetails)principal;

        //주문 번호 생성
        String orderNum = createOrderNum(userDetails.getCustomerId());

        LocalDateTime dateTime = LocalDateTime.now();

        // 주문서 생성
        OrderDetailsDao orderDetailsDao = OrderDetailsDao.builder()
                .orderDetailsId(orderNum)
                .customerId(userDetails.getCustomerId())
                .orderDateTime(dateTime)
                .receiver(orderDetailsDto.getReceiver())
                .phone(orderDetailsDto.getPhone())
                .address(orderDetailsDto.getAddress())
                .zipCode(orderDetailsDto.getZipCode())
                .orderState(OrderState.PREPARE)
                .orderTotalPrice(orderDetailsDto.getTotalPrice())
                .payment(orderDetailsDto.getPayment())
                .build();

        // 주문 내역서의 상품 리스트
        OrderedItemsDto[] itemsList = orderDetailsDto.getOrderedItemsDto();

        // 주문 상품들 배열로 생성
        List<OrderedItemsDao> newDaoOrderedItems = new ArrayList<>();

        for(int i=0; i<itemsList.length; i++) {
            // 상품 상세 내역
            OrderedItemsDao orderedItem = OrderedItemsDao.builder()
                    .orderDetailsId(orderNum)
                    .productId(itemsList[i].getProductId())
                    .productName(itemsList[i].getProductName())
                    .options(String.valueOf(itemsList[i].getProductOptions()))
                    .productPrice(itemsList[i].getProductPrice())
                    .build();

            newDaoOrderedItems.add(orderedItem);
        }

        // 정상적으로 insert가 완료되었으면
        if(orderMapper.insertOrderDetails(orderDetailsDao) == 1 &&
                orderMapper.insertOrderedItems(newDaoOrderedItems) == 1){
            return true;
        }else{
            return false;
        }

    }

    // 주문 번호 생성
    private String createOrderNum(String customerId) {
        StringBuilder sb = new StringBuilder();
        sb.append(customerId);

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        sb.append(year).append(month).append(date).append(hour).append(minute).append(second);

        for(int i=0; i<10; i++) {
            int random = (int) (Math.random() * 10);
            sb.append(random);
        }

        return sb.toString();
    }
}
