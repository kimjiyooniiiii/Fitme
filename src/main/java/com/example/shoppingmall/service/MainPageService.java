package com.example.shoppingmall.service;

import com.example.shoppingmall.entity.File;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.FileMapper;
import com.example.shoppingmall.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final FileMapper fileMapper;

    // mainPage에 보여줄 best 상품 이미지 가져오기
    public List<File> getBestImages(List<Long> bestIdList) {
        List<File> bestProducts =  fileMapper.selectMainImages(bestIdList);

        return bestProducts;
    }
}
