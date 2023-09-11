package com.develop.web.global.exception.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IngestErrorCode implements ErrorCode {

    INGEST_ERROR("I0000", "인제스트 오류 발생"),
    ;

    private final String code;
    private final String message;

    @Override
    public String code() {
        return null;
    }
}
