package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.BestProduct;
import com.example.shoppingmall.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    // 메인 홈페이지
    @GetMapping("/")
    public String getMainPage(Model model) throws ParseException {
        List<Long> bestIdList = new ArrayList<>();
        bestIdList.add(1L);
        bestIdList.add(2L);
        bestIdList.add(3L);

        // mainPage에 보여줄 best 상품 이미지 가져오기
        List<BestProduct> bestProducts = mainPageService.getBestImages(bestIdList);

        model.addAttribute("bestProducts", bestProducts);

        return "mainPage";
    }
}
