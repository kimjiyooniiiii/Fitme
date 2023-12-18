package com.example.shoppingmall.controller;

import com.example.shoppingmall.dao.GetDetailsAndItems;
import com.example.shoppingmall.security.CustomerUserDetails;
import com.example.shoppingmall.service.MyOrderPageService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class MyOrderPageController {

    private final MyOrderPageService myOrderPageService;

    public MyOrderPageController(MyOrderPageService myOrderPageService) {
        this.myOrderPageService = myOrderPageService;
    }

    // 주문내역 불러오기
    @GetMapping("/myOrder")
    public String getMyPage(@AuthenticationPrincipal CustomerUserDetails customerUserDetails, Model model) throws ParseException {

        List<GetDetailsAndItems> orderList = myOrderPageService.getMyPage(customerUserDetails);
        model.addAttribute("orderList", orderList);

        return "myOrder";
    }

   /* // 주문 취소하기
    @DeleteMapping
    public void cancelOrder(Model model){
        boolean isPassed = myOrderPageService.cancelOrder();

        if(!isPassed){
            model.addAttribute("cancelMessage","fail");
        }else{
            model.addAttribute("cancelMessage","success");
        }
    }*/
}
