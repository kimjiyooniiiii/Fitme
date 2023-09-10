package com.example.shoppingmall.controller;

import com.example.shoppingmall.service.CertificationPhoneService;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.cert.Certificate;

@RestController
@RequiredArgsConstructor
public class CertificationPhoneController {

    private final CertificationPhoneService phoneService;

    @PostMapping("/send-sms")
    @ResponseBody
    public String certificatePhoneNumber(String phone) {

        return phoneService.certificatePhoneNumber(phone);

    }
}
