package com.develop.web.domain.service.checker.connect.controller;

import com.develop.web.domain.service.checker.connect.ClientInfoDto;
import com.develop.web.domain.service.checker.connect.service.ClientInfoChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "체크", description = "Swagger 테스트용 API")
@RequestMapping(value = "/check")
public class ConnectController {
    private final ClientInfoChecker clientInfoChecker;

    @GetMapping("/browserInfo")
    public ClientInfoDto browserInfo(HttpServletRequest request) {
        return clientInfoChecker.clientInfo("test", request);
    }
}
