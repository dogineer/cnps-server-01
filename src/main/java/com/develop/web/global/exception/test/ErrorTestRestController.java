package com.develop.web.global.exception.test;

import com.develop.web.global.exception.exception.RestApiException;
import com.develop.web.global.exception.code.CommonErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/error")
@Tag(name = "에러 관리", description = "Exception")
public class ErrorTestRestController {

    @GetMapping("500")
    @Operation(summary = "INVALID_PARAMETER", description = "잘못된 요청 데이터")
    public ResponseEntity<?> INVALID_PARAMETER(){
        throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("404")
    @Operation(summary = "NOT_FOUND", description = "자원이 존재하지 않습니다.")
    public ResponseEntity<?> NOT_FOUND(){
        throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }
}
