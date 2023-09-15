package com.develop.web.global.filter;

import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
@WebFilter(urlPatterns = {"/admin/management/*", "/user/*"})
@ConditionalOnProperty(name = "authentication.type", havingValue = "session")
public class PageRequestAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        try {
            doAuthFilter(session);
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            FilterHandleException.filterException(e, response, session);
        }
    }

    private void doAuthFilter(HttpSession session) {
        log.info("[R] 사용자 인증 필터 실행");

        if (session == null || session.getAttribute("account") == null) {
            log.error("[!] 사용자 인증 실패!");
            throw new CustomException(AuthErrorCode.ACCOUNT_NOT_FOUND);

        } else if (session.getAttribute("access").hashCode() == 0) {
            log.error("[!] 사용자 승인 인증 실패!");
            throw new CustomException(AuthErrorCode.AUTH_ACCESS_NOT_FOUND);
        }

        log.info("[S] 사용자 인증 성공");
        System.out.println("");
    }

    @Override
    public void destroy() {
        log.info("[E] 사용자 인증 필터 종료");
    }
}
