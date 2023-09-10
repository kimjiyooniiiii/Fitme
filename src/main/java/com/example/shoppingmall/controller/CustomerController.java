package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.JoinRequest;
import com.example.shoppingmall.repository.CustomerMapper;
import com.example.shoppingmall.service.CustomerService;
import com.example.shoppingmall.validator.CheckPasswordEqualValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CheckPasswordEqualValidator passwordEqualValidator;       // 비밀번호 정확성 확인

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(passwordEqualValidator);
    }

    // 회원가입 빈 페이지
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinRequest", new JoinRequest());
        return "join";
    }

    // 회원가입 시도
    @PostMapping("/join")
    public String join(@Valid JoinRequest joinRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("joinRequest", joinRequest);
            return "/join";
        } else {
            customerService.join(joinRequest);
            return "redirect:/";
        }
    }

    // 아이디 중복 확인
    @PostMapping("/checkDuplicatedId")
    @ResponseBody
    public HashMap<String, Object> userIdCheck(String userId) {

        return customerService.userIdCheck(userId);
    }
}
