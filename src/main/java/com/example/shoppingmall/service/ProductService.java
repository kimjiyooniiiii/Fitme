package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.ProductFile;
import com.example.shoppingmall.dto.ProductPreview;
import com.example.shoppingmall.entity.File;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.FileMapper;
import com.example.shoppingmall.repository.ProductMapper;
import com.fasterxml.jackson.core.JsonParser;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final FileMapper fileMapper;

    // top 카테고리 페이지 이동
    public List<ProductPreview> getCategoryTop(Long categoryId) throws ParseException {
        List<Map<String, Object>> topProducts = productMapper.selectByCategory(categoryId);

        List<ProductPreview> productList = new ArrayList<>();

        for(Map<String,Object> t : topProducts) {
            // DB에 리스트로 저장되어있는 main_image들을 json 파싱
            String listString = (String) t.get("main_image_list");
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(listString);

            List<String> pathArray = new ArrayList<>();
            for (Object path : jsonArray) {
                pathArray.add((String) path);
            }

            ProductPreview pp = ProductPreview.builder()
                    .productId((Long) t.get("product_id"))
                    .mainImageList(pathArray)
                    .name((String) t.get("name"))
                    .price((Integer) t.get("price"))
                    .build();
            productList.add(pp);
        }

        return productList;
    }

    // skirt 카테고리 페이지 이동
    public List<ProductPreview> getCategorySkirt(Long categoryId) throws ParseException {
        List<Map<String, Object>> skirtProducts = productMapper.selectByCategory(categoryId);

        List<ProductPreview> productList = new ArrayList<>();

        for(Map<String,Object> s : skirtProducts) {
            // DB에 리스트로 저장되어있는 main_image들을 json 파싱
            String listString = (String) s.get("main_image_list");
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(listString);

            List<String> pathArray = new ArrayList<>();
            for (Object path : jsonArray) {
                pathArray.add((String) path);
            }

            ProductPreview pp = ProductPreview.builder()
                    .productId((Long) s.get("product_id"))
                    .mainImageList(pathArray)
                    .name((String) s.get("name"))
                    .price((Integer) s.get("price"))
                    .build();
            productList.add(pp);
        }

        return productList;
    }

    // pants 카테고리 페이지 이동
    public List<ProductPreview> getCategoryPants(Long categoryId) throws ParseException {
        List<Map<String, Object>> pantsProducts = productMapper.selectByCategory(categoryId);

        List<ProductPreview> productList = new ArrayList<>();

        for(Map<String,Object> t : pantsProducts) {
            // DB에 리스트로 저장되어있는 main_image들을 json 파싱
            String listString = (String) t.get("main_image_list");
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(listString);

            List<String> pathArray = new ArrayList<>();
            for (Object path : jsonArray) {
                pathArray.add((String) path);
            }

            ProductPreview pp = ProductPreview.builder()
                    .productId((Long) t.get("product_id"))
                    .mainImageList(pathArray)
                    .name((String) t.get("name"))
                    .price((Integer) t.get("price"))
                    .build();
            productList.add(pp);
        }

        return productList;
    }

    // 상품 상세정보 가져오기
    public ProductFile getProductDetail(Long productId) throws ParseException {
        List<Map<String, Object>> productsDetails = productMapper.selectDetailsById(productId);

        List<String> pathList = new ArrayList<>();
        for(Map<String,Object> p : productsDetails) {
            pathList.add((String) p.get("save_path"));
        }

        // DB(사이즈 배열이 String 형태로 존재) -> jsonParser를 이용하여 List<String>으로 가공
        String sizeString = (String) productsDetails.get(0).get("fit_size");
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(sizeString);
        List<String> sizeArray = new ArrayList<>();
        for (Object fitSize : jsonArray) {
            sizeArray.add((String) fitSize);
        }

        // DB(사이즈 배열이 String 형태로 존재) -> jsonParser를 이용하여 List<String>으로 가공
        String colorString = (String) productsDetails.get(0).get("color");
        JSONParser colorParser = new JSONParser();
        JSONArray jsonColorArray = (JSONArray) colorParser.parse(colorString);
        List<String> colorArray = new ArrayList<>();
        for (Object color : jsonColorArray) {
            colorArray.add((String) color);
        }

        // Dto 생성
        ProductFile pf = ProductFile.builder()
                .productId((Long) productsDetails.get(0).get("product_id"))
                .name((String) productsDetails.get(0).get("name"))
                .price((Integer) productsDetails.get(0).get("price"))
                .shortDescrip((String) productsDetails.get(0).get("short_descrip"))
                .fitSize(sizeArray)
                .color(colorArray)
                .savePath(pathList)
                .build();

        return pf;
    }

}
