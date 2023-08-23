package com.example.shoppingmall.service;

import com.example.shoppingmall.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    //private final ProductMapper productRepository;

    public Product getProductDetail(Long productId) {
        Product product = new Product();

        return product;
    }
}
