package com.develop.web.global.exception.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode  {

    INVALID_PARAMETER("C0000", "잘못된 요청 데이터가 포함되어 있습니다."),
    RESOURCE_NOT_FOUND("C0001", "자원이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR("C0002", "인터넷 서버 오류"),
    ;

    private final String code;
    private final String message;

}
