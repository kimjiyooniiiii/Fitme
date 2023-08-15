package com.example.shoppingmall.controller;

import com.example.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/productDetail/{id}")
    public String getProductDetail(@PathVariable("id") Long productId, Model model) {
        model.addAttribute(productService.getProductDetail(productId));

        return "productDetail";
    }
}
