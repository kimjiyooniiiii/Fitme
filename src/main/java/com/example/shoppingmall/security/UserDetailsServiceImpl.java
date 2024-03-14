package com.example.shoppingmall.security;

import com.example.shoppingmall.dao.CustomerDao;
import com.example.shoppingmall.repository.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        CustomerDao customerDao = customerMapper.findCustomerInfo(userId);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        CustomerUserDetails userDetails = CustomerUserDetails.builder()
                .customerId(customerDao.getCustomerId())
                .password(customerDao.getPassword())
                .name(customerDao.getName())
                .phone(customerDao.getPhone())
                .authorities(grantedAuthorities)
                .build();

        return userDetails;
    }
}
