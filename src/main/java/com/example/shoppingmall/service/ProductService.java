package com.example.shoppingmall.service;

import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    // top 카테고리 페이지 이동
    public List<Product> getCategoryTop(Long categoryId) {
        List<Product> topProducts = productMapper.selectByCategory(categoryId);

        return topProducts;
    }

    // skirt 카테고리 페이지 이동
    public List<Product> getCategorySkirt(Long categoryId) {
        List<Product> skirtProducts = productMapper.selectByCategory(categoryId);

        return skirtProducts;
    }

    // pants 카테고리 페이지 이동
    public List<Product> getCategoryPants(Long categoryId) {
        List<Product> pantsProducts = productMapper.selectByCategory(categoryId);

        return pantsProducts;
    }

    // 상품 상세페이지 보기
    public Product getProductDetail(Long productId) {
        Product product = productMapper.selectDetailsById(productId);

        return product;
    }
}
