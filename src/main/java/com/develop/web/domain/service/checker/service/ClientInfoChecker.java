package com.develop.web.domain.service.checker.service;

import com.develop.web.domain.service.checker.dto.ClientInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientInfoChecker {
    private final CheckClientBrowser checkClientBrowser;
    private final CheckClientOS checkClientOS;

    public ClientInfoDto clientInfo(String account, HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("X-Forwarded-For");
        String agent = httpServletRequest.getHeader("USER-AGENT");
        String os = checkClientOS.getClientOS(agent);
        String browser = checkClientBrowser.getClientBrowser(agent);

        if (ip == null || ip.length() == 0 ||
            ip.equalsIgnoreCase("unknown"))
            ip = httpServletRequest.getRemoteAddr();

        ClientInfoDto clientInfo = new ClientInfoDto(agent, os, browser, ip);

        log.info("[+] ⌜ ⎺ ⎺ ⎺ ⎺ ⎺ ⎺ ⎺ ⎺ ⎺ ⎺ ⌝");
        log.info("[|] '" + account + "' 님이 접속했습니다.");
        log.info("[|] ip: " + clientInfo.getIp());
        log.info("[|] header: " + clientInfo.getHeader());
        log.info("[|] os: " + clientInfo.getOs());
        log.info("[|] browser: " + clientInfo.getBrowser());
        log.info("[+] ⌞ _ _ _ _ _ _ _ _ _ _ ⌟");

        return clientInfo;
    }
}
