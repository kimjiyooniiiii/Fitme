package com.example.shoppingmall.controller;

import com.example.shoppingmall.security.CustomerUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/order")
    public String loadOrderPage(@AuthenticationPrincipal CustomerUserDetails customerUserDetails, Model model){

        // 주문서에 자동으로 로드되는 고객 연락처
        model.addAttribute("phone", customerUserDetails.getPhone());

        return "order";
    }
}
