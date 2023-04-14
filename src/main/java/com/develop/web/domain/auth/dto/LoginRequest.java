package com.develop.web.domain.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String account;
    private String password;
}
