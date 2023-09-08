package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.JoinRequest;
import com.example.shoppingmall.entity.Customer;
import com.example.shoppingmall.repository.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    /*private final CustomerMapper customerMapper;*/

//    public void join(JoinRequest joinRequest) {
//        Customer customer = Customer.builder()
//                .customerId(joinRequest.getId())
//                .name(joinRequest.getName())
//                .phone(joinRequest.getPhone())              // 인증번호
//                .password(joinRequest.getPassword())       //암호화, 일치확인
//                .build();
//
//        customerMapper.joinNewCustomer(customer);   //예외처리
//    }
}
