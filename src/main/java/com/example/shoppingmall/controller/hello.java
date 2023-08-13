package com.example.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hello {

    @GetMapping("/")
    public String hello() {
        return "mainPage";
    }
}
