package com.example.shoppingmall.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;

    @NotBlank(message = "비밀번호 입력해주세요")
    private String password;

    @NotBlank(message = "비밀번호를 확인해주세요")
    private String passwordConfirm;

    @Pattern(regexp = "^010-([0-9]{4})-([0-9]{4})$", message = "전화번호는 010-0000-0000 형식으로 입력해주세요")
    private String phone;

    @Builder
    public JoinRequest(String name, String userId, String password, String passwordConfirm, String phone) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.phone = phone;
    }
}
