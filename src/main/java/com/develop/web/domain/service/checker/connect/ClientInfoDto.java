package com.develop.web.domain.service.checker.connect;

import lombok.Getter;

@Getter
public class ClientInfoDto {
    private final String header;
    private final String os;
    private final String browser;
    private final String ip;

    public ClientInfoDto(String header, String os, String browser, String ip) {
        this.header = header;
        this.os = os;
        this.browser = browser;
        this.ip = ip;
    }
}
