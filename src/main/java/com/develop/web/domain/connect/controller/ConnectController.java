package com.develop.web.domain.connect.controller;

import com.develop.web.domain.connect.ClientInfoDto;
import com.develop.web.domain.connect.service.ClientInfoChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ConnectController {
    private final ClientInfoChecker clientInfoChecker;

    @GetMapping("/check-broswserInfo")
    public ClientInfoDto broswserInfo(HttpServletRequest request) {
        return clientInfoChecker.clientInfo("test", request);
    }
}
