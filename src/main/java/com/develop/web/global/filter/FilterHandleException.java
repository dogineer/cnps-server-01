package com.develop.web.global.filter;

import com.develop.web.global.exception.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class FilterHandleException {
    public static void filterException(CustomException e, HttpServletResponse response) throws IOException {
        log.error("[!] Error Code : " + e.getErrorCode());
        log.error("[!] Error Message : " + e.getMessage());
        System.out.println("");

        String redirectUrl = "/error/view?error=" + e.getErrorCode().name()
            + "&code=" + e.getErrorCode().code()
            + "&message=" + URLEncoder.encode(e.getErrorCode().getMessage(), StandardCharsets.UTF_8);

        response.sendRedirect(redirectUrl);
    }
}
