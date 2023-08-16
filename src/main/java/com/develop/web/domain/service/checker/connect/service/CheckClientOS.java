package com.develop.web.domain.service.checker.connect.service;

import org.springframework.stereotype.Service;

@Service
public class CheckClientOS {
    public String getClientOS(String userAgent) {
        String os;

        userAgent = userAgent.toLowerCase();

        if (userAgent.contains("windows nt 10.0")) {
            os = "Windows10";
        } else if (userAgent.contains("windows nt 6.1")) {
            os = "Windows7";
        } else if (userAgent.contains("windows nt 6.2") || userAgent.contains("windows nt 6.3")) {
            os = "Windows8";
        } else if (userAgent.contains("windows nt 6.0")) {
            os = "WindowsVista";
        } else if (userAgent.contains("windows nt 5.1")) {
            os = "WindowsXP";
        } else if (userAgent.contains("windows nt 5.0")) {
            os = "Windows2000";
        } else if (userAgent.contains("windows nt 4.0")) {
            os = "WindowsNT";
        } else if (userAgent.contains("windows 98")) {
            os = "Windows98";
        } else if (userAgent.contains("windows 95")) {
            os = "Windows95";
        } else if (userAgent.contains("iphone")) {
            os = "iPhone";
        } else if (userAgent.contains("ipad")) {
            os = "iPad";
        } else if (userAgent.contains("android")) {
            os = "android";
        } else if (userAgent.contains("mac")) {
            os = "mac";
        } else if (userAgent.contains("linux")) {
            os = "Linux";
        } else {
            os = "Other";
        }
        return os;
    }
}
