package com.develop.web.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {

    TOKEN_NO_ACCESS("T0000","토큰 권한이 없습니다."),
    INVALID_JWT_TOKEN("T0001", "토큰이 유효하지 않습니다."),
    EXPIRED_JWT_TOKEN("T0002", "만료된 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN("T0003", "지원하지 않는 토큰입니다."),
    STRING_IS_EMPTY("T0004", "문자열이 비어있습니다."),
    JWT_CLAIMS_STRING_IS_EMPTY("T0005", "JWT 클레임 문자열이 비어 있습니다.")
    ;

    private final String code;
    private final String message;

    @Override
    public String code() {
        return this.code;
    }
}
