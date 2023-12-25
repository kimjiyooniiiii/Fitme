package com.example.shoppingmall.service;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CertificationPhoneService {

    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecretKey;

    @Value("${coolsms.from.phone}")
    private String from;

    private DefaultMessageService messageService;

    @PostConstruct
    private void init(){
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    public String certificatePhoneNumber(String phone) {

        Random rand = new Random();
        String certificateNum = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            certificateNum += ran;
        }

        Message message = new Message();
        message.setFrom(from);
        phone = phone.replace("-","");
        message.setTo(phone);
        message.setText("인증번호는 " + certificateNum + "입니다.");

        this.messageService.sendOne(new SingleMessageSendingRequest(message));

        return certificateNum;
    }
}
