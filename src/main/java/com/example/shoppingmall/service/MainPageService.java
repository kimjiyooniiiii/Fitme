package com.example.shoppingmall.service;

import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final ProductMapper productMapper;

    // mainPage에 보여줄 best 상품 이미지 가져오기
    public List<String> getBestImage(List<Long> bestIdList) {
        List<Product> bestProducts =  productMapper.selectBestById(bestIdList);
        List<String> bestImages = new ArrayList<>();

        for(Product p : bestProducts) {
            bestImages.add(p.getMainImage());
        }

        return bestImages;
    }
}
