package com.example.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketController {

    @GetMapping("/myBasket")
    public String myBasket() {
        return "myBasket";
    }
}
