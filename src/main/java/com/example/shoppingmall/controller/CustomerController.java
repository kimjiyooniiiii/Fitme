package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.JoinRequest;
import com.example.shoppingmall.service.CustomerService;
import com.example.shoppingmall.validator.CheckPasswordEqualValidator;
import com.example.shoppingmall.validator.CheckUserIdValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CheckUserIdValidator userIdValidator;     // 중복 아이디 확인
    private final CheckPasswordEqualValidator passwordEqualValidator;       // 비밀번호 정확성 확인

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(userIdValidator);
        binder.addValidators(passwordEqualValidator);
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinRequest", new JoinRequest());
        return "join";
    }

    @PostMapping("/join")
    public String join(@Valid JoinRequest joinRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("joinRequest", joinRequest);

            return "/join";
        } else {
            /*customerService.join(joinRequest);*/
            System.out.println(joinRequest.getId());
            System.out.println(joinRequest.getName());
            System.out.println(joinRequest.getPassword());
            System.out.println(joinRequest.getPhone());

            return "redirect:/";
        }
    }
}
