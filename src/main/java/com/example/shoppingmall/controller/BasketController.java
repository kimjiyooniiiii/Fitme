package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.BasketItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasketController {

    // 장바구니 추가
    //@PostMapping("/basket/add")
    @ResponseBody
    public String addBasket(@RequestBody BasketItem basketItem) {


        return "성공";
    }

    @GetMapping("/myBasket")
    public String myBasket() {
        /*List<BasketProduct> basketList = new ArrayList<>();

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

        model.addAttribute("basketList",basketList);*/
        return "myBasket";
    }
}
