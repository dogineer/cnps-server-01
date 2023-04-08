package com.develop.web.domain.auth.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String userid;
    private String password;
}
