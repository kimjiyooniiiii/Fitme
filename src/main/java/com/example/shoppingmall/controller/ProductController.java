package com.example.shoppingmall.controller;

import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // top 카테고리 페이지 이동
    @GetMapping("/categoryProduct/top")
    public String getCategoryTop(Model model) {
        List<Product> topProducts = productService.getCategoryTop(1L);
        model.addAttribute("topProducts", topProducts);

        return "categoryProduct";
    }

    @GetMapping("/productDetail/{id}")
    public String getProductDetail(@PathVariable("id") Long productId, Model model) {
        model.addAttribute(productService.getProductDetail(productId));

        return "productDetail";
    }

    @GetMapping("/productDetail")
    public String productDetail() {
        return "productDetail";
    }
}
