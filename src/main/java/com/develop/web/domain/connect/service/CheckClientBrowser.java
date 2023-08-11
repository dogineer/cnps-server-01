package com.develop.web.domain.connect.service;

import org.springframework.stereotype.Service;

@Service
public class CheckClientBrowser {
    public String getClientBrowser(String userAgent) {
        String browser;

        if (userAgent.contains("Trident/7.0")) {
            browser = "ie11";
        } else if (userAgent.contains("MSIE 10")) {
            browser = "ie10";
        } else if (userAgent.contains("MSIE 9")) {
            browser = "ie9";
        } else if (userAgent.contains("MSIE 8")) {
            browser = "ie8";
        } else if (userAgent.contains("Chrome/")) {
            browser = "Chrome";
        } else if (userAgent.contains("Safari/")) {
            browser = "Safari";
        } else if (userAgent.contains("Firefox/")) {
            browser = "Firefox";
        } else {
            browser = "Other";
        }
        return browser;
    }
}
