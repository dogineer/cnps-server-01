package com.develop.web.global.exception.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeptErrorCode implements ErrorCode {

    JOIND_MEMEBER("D0001", "삭제할려는 부서에 가입된 멤버가 존재합니다."),
    ;

    private final String code;
    private final String message;

    @Override
    public String code() {
        return this.code;
    }
}
