package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.BasketItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasketController {

    @ResponseBody
    public String addBasket(@RequestBody BasketItem basketItem) {
        return "성공";
    }

    // 장바구니 페이지
    @GetMapping("/myBasket")
    public String myBasket() {

        return "myBasket";
    }

    // 주문하기
    @GetMapping("/order")
    public String order() {

        return "order";
    }
}
