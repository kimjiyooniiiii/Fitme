package com.example.shoppingmall.controller;

import com.example.shoppingmall.service.CertificationPhoneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CertificationPhoneController {

    private final CertificationPhoneService phoneService;

    @PostMapping("/send-sms")
    @ResponseBody
    public String certificatePhoneNumber(@RequestBody Map<String, String> map) throws JsonProcessingException {

        String certificateNum = phoneService.certificatePhoneNumber(map.get("phone"));

        Map<String, String> result = new HashMap<>();
        result.put("num",certificateNum);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(result);

    }
}
