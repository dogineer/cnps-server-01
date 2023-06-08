package com.develop.web.domain.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class LoginRequest {
    private final String account;
    private final String password;
}
