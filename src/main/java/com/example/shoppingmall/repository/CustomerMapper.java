package com.example.shoppingmall.repository;

import com.example.shoppingmall.dto.NewCustomer;
import com.example.shoppingmall.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

// mapper 인터페이스
@Mapper // spring에 bean으로 등록
public interface CustomerMapper {

    // 신규가입 - 성공시 1반환
    int joinNewCustomer(NewCustomer customer);

    // 중복 아이디 확인
    boolean findById(String id);
}
