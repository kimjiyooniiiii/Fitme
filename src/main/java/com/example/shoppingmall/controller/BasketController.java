package com.example.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketController {

    // 장바구니 페이지
    @GetMapping("/myBasket")
    public String myBasket() {

        return "myBasket";
    }

}
