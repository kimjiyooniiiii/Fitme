package com.example.shoppingmall.security;

import com.example.shoppingmall.entity.Customer;
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

        Customer customer = customerMapper.findCustomerInfo(userId);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        CustomerUserDetails userDetails = CustomerUserDetails.builder()
                .customerId(customer.getCustomerId())
                .password(customer.getPassword())
                .name(customer.getName())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .authorities(grantedAuthorities)
                .build();

        return userDetails;
    }
}
