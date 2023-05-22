package com.develop.web.domain.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class AuthChecker {
    /**
     * @description 멤버 권한 체크 서비스
     * */
    public void blockOutsiders(HttpSession session){

        boolean account = session.getAttribute("account") == null;
        boolean access = session.getAttribute("access").hashCode() == 0;

        if (account){
            throw new UsernameNotFoundException("세션이 없습니다.");
        } else if (access) {
                throw new AccessDeniedException("승인되지 않은 계정입니다. 관리자 승인 요청");
            }

    }
}
