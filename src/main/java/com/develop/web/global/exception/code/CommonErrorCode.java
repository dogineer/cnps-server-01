package com.develop.web.global.exception.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청 데이터가 포함되어 있습니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "자원이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "인터넷 서버 오류"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
