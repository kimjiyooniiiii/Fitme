package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ProductFile;
import com.example.shoppingmall.entity.File;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "categoryTop";
    }

    // skirt 카테고리 페이지 이동
    @GetMapping("/categoryProduct/skirt")
    public String getCategorySkirt(Model model) {
        List<Product> skirtProducts = productService.getCategorySkirt(2L);
        model.addAttribute("skirtProducts", skirtProducts);

        return "categorySkirt";
    }

    // pants 카테고리 페이지 이동
    @GetMapping("/categoryProduct/pants")
    public String getCategoryPants(Model model) {
        List<Product> pantsProducts = productService.getCategoryPants(3L);
        model.addAttribute("pantsProducts", pantsProducts);

        return "categoryPants";
    }

    // 상품 상세보기 페이지
    @GetMapping("/productDetail")
    public String getProductDetail(@RequestParam(value = "id") Long productId, Model model) {
        List<ProductFile> product = productService.getProductDetail(productId);
        model.addAttribute("product", product);

        return "productDetail";
    }

    /*@GetMapping("/productDetail")
    public String productDetail() {
        return "productDetail";
    }*/
}
