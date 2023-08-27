package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    List<File> selectMainImages(List<Long> isList);
}
