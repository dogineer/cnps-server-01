package com.develop.web.global.exception.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FolderErrorCode implements ErrorCode {

    EXISTENCE_FOLDER("F0001", "하위 폴더가 존재합니다."),
    ;

    private final String code;
    private final String message;

    @Override
    public String code() {
        return this.code;
    }
}
