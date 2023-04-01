package com.develop.web.domain.auth.vo;

import lombok.Getter;

@Getter
public class UserLoginRequest {
    public UserLoginRequest(String userid, String password, Role role) {
        this.userid = userid;
        this.password = password;
        this.role = role;
    }

    private String userid;
    private String password;
    private Role role;
}
