package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    //메인 화면의 best 상품 이미지 가져오기
    List<File> selectMainImages(List<Long> isList);
}
