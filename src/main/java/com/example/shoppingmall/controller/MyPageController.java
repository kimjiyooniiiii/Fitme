package com.example.shoppingmall.controller;

import com.example.shoppingmall.dao.GetDetailsAndItems;
import com.example.shoppingmall.security.CustomerUserDetails;
import com.example.shoppingmall.service.MyPageService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    //마이페이지 : 주문내역 불러오기
    @GetMapping("/myPage")
    public String getMyPage(@AuthenticationPrincipal CustomerUserDetails customerUserDetails, Model model) throws ParseException {

        List<GetDetailsAndItems> orderList = myPageService.getMyPage(customerUserDetails);
        model.addAttribute("orderList", orderList);

        return "myPage";
    }
}
