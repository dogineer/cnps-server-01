package com.develop.web.global.filter.session;

import com.develop.web.domain.admin.user.dto.Role;
import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import com.develop.web.global.filter.FilterHandleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@WebFilter(urlPatterns = {"/admin/*"})
@ConditionalOnProperty(name = "authentication.type", havingValue = "session")
public class AdminPageRequestRankFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        try {
            doRoleFilter(session);
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            FilterHandleException.filterException(e, response, session);
        }
    }

    private void doRoleFilter(HttpSession session) {
        log.info("[Authority] 사용자 권한 필터 실행");

        boolean isAdmin = Objects.equals(session.getAttribute("role"), Role.ADMIN.getAuthority());

        if (!isAdmin) {
            log.error("[!] 관리자 권한 인증 실패!");
            throw new CustomException(AuthErrorCode.NO_ACCESS);
        }

        log.info("[Success] 관리자 인증 성공\n");
    }

    @Override
    public void destroy() {
        log.info("[End] 사용자 권한 필터 종료");
    }
}
