package com.develop.web.global.exception;


import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_PARAMETER(400, null, "잘못된 요청 데이터"),
    PAGE_NOT_FOUND(404, "", "페이지가 없습니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
