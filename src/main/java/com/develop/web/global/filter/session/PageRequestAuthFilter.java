package com.develop.web.global.filter.session;

import com.develop.web.domain.admin.user.dto.Role;
import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import com.develop.web.global.filter.FilterHandleException;
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
import java.util.Objects;

@Slf4j
@Component
@WebFilter(urlPatterns = {"/admin/management/*", "/service/*"})
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
        log.info("[Certification] 사용자 인증 필터 실행");

        if (session == null || session.getAttribute("account") == null) {
            log.error("[!] 사용자 인증 실패!");
            throw new CustomException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        }

        boolean isAnonymous = Objects.equals(session.getAttribute("role"), Role.ANONYMOUS.getAuthority());

        if (isAnonymous) {
            log.error("[!] 사용자 승인 인증 실패!");
            throw new CustomException(AuthErrorCode.AUTH_ACCESS_NOT_FOUND);
        }

        log.info("[Success] 사용자 인증 성공\n");
    }

    @Override
    public void destroy() {
        log.info("[End] 사용자 인증 필터 종료");
    }
}
