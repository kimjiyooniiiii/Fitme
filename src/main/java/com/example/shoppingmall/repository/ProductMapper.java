package com.example.shoppingmall.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    /*메인 화면의 best 상품 이미지 가져오기*/
    List<Map<String, Object>> selectBestById(List<Long> bestIdList);

    /*카테고리별 상품 목록*/
    List<Map<String, Object>> selectByCategory(Long categoryId);

    /*상품 상세정보 가져오기*/
    Map<String, Object> selectDetailsById(Long productId);
}
