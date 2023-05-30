package com.develop.web.global.exception.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    DUPLICATE_MEMBER("M0001", "아이디가 중복입니다."),
    PASSWORD_NOW_MATCH("M0002", "비밀번호가 맞지 않습니다.")
    ;

    private final String code;
    private final String message;

    @Override
    public String code() {
        return this.code;
    }
}
