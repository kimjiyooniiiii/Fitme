package com.example.shoppingmall.validator;

import com.example.shoppingmall.dto.JoinRequest;
import com.example.shoppingmall.repository.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUserIdValidator extends AbstractValidator<JoinRequest> {      // 아이디 중복확인

    private final CustomerMapper customerMapper;

    @Override
    protected void doValidate(JoinRequest dto, Errors errors) {
        if(customerMapper.findById(dto.getId())) {
            errors.rejectValue("id", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
