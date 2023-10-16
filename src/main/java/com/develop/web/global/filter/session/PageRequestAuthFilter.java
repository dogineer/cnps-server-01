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
@WebFilter(urlPatterns = {
    "/s1/page/management/**",
    "/s1/page/**"})
@ConditionalOnProperty(name = "authentication.type", havingValue = "session")
public class PageRequestAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        try {
            doSessionAuthFilter(session);
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            FilterHandleException.filterException(e, response, session);
        }
    }

    private void doSessionAuthFilter(HttpSession session) {
        log.info("[Certification] 사용자 인증 필터 실행");

        if (session == null) {
            throw new CustomException(AuthErrorCode.SESSION_NOT_FOUND);
        }

        if (session.getAttribute("account") == null) {
            sessionInfo(session);
            throw new CustomException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        }

        boolean isAnonymous = Objects.equals(session.getAttribute("role"), Role.ANONYMOUS.getAuthority());

        if (isAnonymous) {
            sessionInfo(session);
            throw new CustomException(AuthErrorCode.AUTH_ACCESS_NOT_FOUND);
        }

        log.info("[Success] 사용자 인증 성공");
        sessionInfo(session);
    }

    private void sessionInfo(HttpSession session) {
        log.info("[Session] 현재 세션 ID: " + session.getId());
        log.info("[Session] 현재 세션 ACCOUNT: " + session.getAttribute("account") + "\n");
    }

    @Override
    public void destroy() {
        log.info("[End] 사용자 인증 필터 종료");
    }
}
