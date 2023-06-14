package com.develop.web.global.exception.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeErrorCode implements ErrorCode {

    NOTICE_NO_DATA_AVAILABLE("N0001", "공지사항 데이터가 존재하지 않습니다."),
    ;

    private final String code;
    private final String message;

    @Override
    public String code() {
        return this.code;
    }
}
