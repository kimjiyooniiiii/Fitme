package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.BasketItem;
import com.example.shoppingmall.dto.BasketList;
import com.example.shoppingmall.dto.BasketProduct;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BasketController {

    // 장바구니 추가
    @PostMapping("/basket/push")
    public void addBasket(BasketItem basketItem, int deliveryTip, HttpSession session) {

        BasketList sessionBasketList = (BasketList) session.getAttribute("basketList");

        if(sessionBasketList == null) {
            List<BasketItem> newBasket = new ArrayList<>();
            newBasket.add(basketItem);
            sessionBasketList = new BasketList(newBasket, basketItem.getTotalPrice(), deliveryTip);
        }else{
            // 총가격 재계산
            int prevTotalPrice = sessionBasketList.getTotalPrice();
            sessionBasketList.setTotalPrice((prevTotalPrice + basketItem.getTotalPrice()));

            List<BasketItem> prevBasket = sessionBasketList.getBasketList();

            // 같은 상품이 있을 경우
            if(prevBasket.contains(basketItem)){
                int index = prevBasket.indexOf(basketItem);
                BasketItem getItem = prevBasket.get(index);

                int totalPrice = basketItem.getProductPrice();
                int newTotalPrice = totalPrice + getItem.getTotalPrice();
                getItem.setTotalPrice(newTotalPrice);
                prevBasket.set(index, getItem);
            }else {
                prevBasket.add(basketItem);
            }
        }
        session.setAttribute("basketList", sessionBasketList);
    }

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
