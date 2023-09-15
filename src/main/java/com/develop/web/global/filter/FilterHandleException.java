package com.develop.web.global.filter;

import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class FilterHandleException {
    public static void filterException(CustomException e, HttpServletResponse response, HttpSession session) throws IOException {
        log.error("[!] Error Code : " + e.getErrorCode());
        log.error("[!] Error Message : " + e.getErrorCode().getMessage());
        System.out.println("");

        if (e.getErrorCode() == AuthErrorCode.AUTH_ACCESS_NOT_FOUND){
            session.invalidate();
        }

        String redirectUrl = "/error/view?error=" + e.getErrorCode().name()
            + "&code=" + e.getErrorCode().code()
            + "&message=" + URLEncoder.encode(e.getErrorCode().getMessage(), StandardCharsets.UTF_8);

        response.sendRedirect(redirectUrl);
    }
}
