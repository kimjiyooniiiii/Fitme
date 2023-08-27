package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ProductMapper {

    /*메인 화면의 best 상품 이미지 가져오기*/
    List<Product> selectBestById(List<Long> bestIdList);

    /*카테고리별 상품 목록*/
    List<Product> selectByCategory(Long categoryId);

    /*상품 상세정보 보기*/
    Product selectDetailsById(Long productId);
}
