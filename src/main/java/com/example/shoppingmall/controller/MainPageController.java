package com.example.shoppingmall.controller;

import com.example.shoppingmall.entity.File;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.service.MainPageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    @GetMapping("/")
    public String getMainPage(Model model) {
        List<Long> bestIdList = new ArrayList<>();
        bestIdList.add(1L);
        bestIdList.add(2L);
        bestIdList.add(3L);

        // mainPage에 보여줄 best 상품 이미지 가져오기
        List<File> bestProducts = mainPageService.getBestImages(bestIdList);

        model.addAttribute("bestProducts", bestProducts);

        return "mainPage";
    }

    @GetMapping("/detail")
    public String detail() {
        return "productDetail";
    }

}
