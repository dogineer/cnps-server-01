package com.develop.web.domain.auth.vo;

import lombok.Getter;

@Getter
public class PasswordChangeRequest {
    public PasswordChangeRequest(String password, String passwordChangeData) {
        this.password = password;
        this.passwordChangeData = passwordChangeData;
    }

    private String password;            // 패스워드
    private String passwordChangeData;  // 패스워드 변경
}
