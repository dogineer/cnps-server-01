package com.develop.web.domain.users.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class LoginDto {
    private final String account;
    private final String password;
}
