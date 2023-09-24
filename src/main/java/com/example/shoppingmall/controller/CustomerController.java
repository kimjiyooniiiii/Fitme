package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.JoinRequest;
import com.example.shoppingmall.service.CustomerService;
import com.example.shoppingmall.validator.CheckPasswordEqualValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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
            return "join";
        } else {
            customerService.join(joinRequest);
            return "redirect:/";
        }
    }

    // 아이디 중복 확인
    @PostMapping("/checkDuplicatedId")
    @ResponseBody
    public String userIdCheck(@RequestBody Map<String, String> map) throws JsonProcessingException {
        HashMap<String, Object> result = customerService.userIdCheck(map.get("userId"));

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(result);
    }

    // 로그인 성공 페이지
    @GetMapping("/loginSuccess")
    public String loginSuccess(Authentication auth) {
        if(auth!=null) {
            System.out.println(auth.getName());
        }else{
            System.out.println("회원없음");
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request,response,SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/";
    }
}
