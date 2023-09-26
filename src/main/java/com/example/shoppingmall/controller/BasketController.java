package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.BasketProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BasketController {

    @GetMapping("/myBasket")
    public String myBasket(Model model) {
        List<BasketProduct> basketList = new ArrayList<>();

        BasketProduct basketProduct = BasketProduct.builder()
                .productName("상품 1")
                .count(1)
                .image("https://shoppingmall-jiyoon.s3.ap-northeast-2.amazonaws.com/pants/%EB%8D%B0%EB%8B%98%EB%A9%9C%EB%B9%B5%ED%8C%AC%EC%B8%A0_main.jpg")
                .sum(10000)
                .build();

        BasketProduct basketProduct2 = BasketProduct.builder()
                .productName("상품 2")
                .count(1)
                .image("https://shoppingmall-jiyoon.s3.ap-northeast-2.amazonaws.com/pants/%EB%8D%B0%EB%8B%98%EB%A9%9C%EB%B9%B5%ED%8C%AC%EC%B8%A0_main.jpg")
                .sum(30000)
                .build();

        basketList.add(basketProduct);
        basketList.add(basketProduct2);

        model.addAttribute("basketList",basketList);
        return "myBasket";
    }
}
