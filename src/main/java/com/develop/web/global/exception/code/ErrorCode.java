package com.develop.web.global.exception.code;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getHttpStatus();
    String name();
    String getMessage();
}
