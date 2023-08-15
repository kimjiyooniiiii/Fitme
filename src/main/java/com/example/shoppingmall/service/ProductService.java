package com.example.shoppingmall.service;

import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    //private final ProductRepository productRepository;

    public Product getProductDetail(Long productId) {
        Product product = Product.builder().build();

        return product;
    }
}
