package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

// mapper 인터페이스
@Mapper // spring에 bean으로 등록
public interface CustomerMapper {

    /*void joinNewCustomer(Customer customer);*/

    boolean findById(String id);
}
