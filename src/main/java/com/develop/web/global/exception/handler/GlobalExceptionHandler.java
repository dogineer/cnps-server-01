package com.develop.web.global.exception.handler;

import com.develop.web.global.exception.code.CommonErrorCode;
import com.develop.web.global.exception.code.ErrorCode;
import com.develop.web.global.exception.response.ErrorResponse;
import com.develop.web.global.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /* 런타임 에러 처리 */
    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleCustomException(RestApiException e) {
        return handleExceptionInternal(e.getErrorCode());
    }

    /* 널 에러 처리 */
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
        log.error("handleNullPointerException", e);
        return handleExceptionInternal(CommonErrorCode.RESOURCE_NOT_FOUND);
    }

    /* 모든 에러 처리 */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception e) {
        log.warn("handleAllException", e);
        return handleExceptionInternal(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(buildErrorResponse(errorCode));
    }

    private ErrorResponse buildErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
            .code(errorCode.name())
            .message(errorCode.getMessage())
            .build();
    }
}
