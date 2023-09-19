package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.JoinRequest;
import com.example.shoppingmall.dto.NewCustomer;
import com.example.shoppingmall.repository.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 시도
    public void join(JoinRequest joinRequest) {
        NewCustomer customer = NewCustomer.builder()
                .customerId(joinRequest.getId())
                .name(joinRequest.getName())
                .phone(joinRequest.getPhone())
                .password(bCryptPasswordEncoder.encode(joinRequest.getPassword()))       //암호화
                .build();

        customerMapper.joinNewCustomer(customer);
    }

    // 아이디 중복 확인
    public HashMap<String, Object> userIdCheck(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", customerMapper.findById(userId));

        return map;
    }
}
