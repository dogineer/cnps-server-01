package com.develop.web.global.exception.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "account 세션 null"),
    AUTH_ACCESS_NOT_FOUND(HttpStatus.UNAUTHORIZED, "승인된 계정이 아닙니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
