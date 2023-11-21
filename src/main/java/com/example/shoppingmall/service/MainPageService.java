package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.BestProduct;
import com.example.shoppingmall.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final ProductMapper productMapper;

    // mainPage에 보여줄 best 상품 이미지 가져오기
    public List<BestProduct> getBestImages(List<Long> bestIdList) throws ParseException {
        List<Map<String, Object>> bestProducts =  productMapper.selectBestById(bestIdList);

        List<BestProduct> bestProductList = new ArrayList<>();

        for(Map<String,Object> b : bestProducts) {
            // DB에 리스트로 저장되어있는 main_image들을 json 파싱
            String listString = (String) b.get("main_image_list");
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(listString);

            List<String> pathArray = new ArrayList<>();
            for (Object path : jsonArray) {
                pathArray.add((String) path);
            }

            BestProduct pp = BestProduct.builder()
                    .productId((Long) b.get("product_id"))
                    .mainImageList(pathArray)
                    .build();
            bestProductList.add(pp);
        }

        return bestProductList;
    }
}
