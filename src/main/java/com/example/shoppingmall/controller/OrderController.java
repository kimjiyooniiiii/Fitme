package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CustomerDto;
import com.example.shoppingmall.dto.OrderDetailsDto;
import com.example.shoppingmall.security.CustomerUserDetails;
import com.example.shoppingmall.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문하기 페이지
    @GetMapping("/order")
    public String loadOrderPage(@AuthenticationPrincipal CustomerUserDetails customerUserDetails, Model model){

        CustomerDto customerDto = CustomerDto.builder()
                .customerId(customerUserDetails.getCustomerId())
                .name(customerUserDetails.getName())
                .phone(customerUserDetails.getPhone())
                .build();

        // 로그인 된 고객 정보
        model.addAttribute("customerDto", customerDto);

        return "order";
    }

    // 주문 로직
    @PostMapping("/orderComplete")
    @ResponseBody
    public boolean orderComplete(@RequestBody OrderDetailsDto orderDetailsDto){
        boolean insertSuccess = orderService.orderComplete(orderDetailsDto);

        return insertSuccess;
    }
}
