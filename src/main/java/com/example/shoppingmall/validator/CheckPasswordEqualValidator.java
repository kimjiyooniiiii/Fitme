package com.example.shoppingmall.validator;

import com.example.shoppingmall.dto.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

// 비밀번호 정확성 확인
@RequiredArgsConstructor
@Component
public class CheckPasswordEqualValidator extends AbstractValidator<JoinRequest> {

    @Override
    protected void doValidate(JoinRequest dto, Errors errors) {
        if(!dto.getPassword().equals(dto.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "비밀번호 불일치", "비밀번호를 확인해주세요!");
        }
    }
}
