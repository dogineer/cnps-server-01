package com.develop.web.domain.auth.service;

import com.develop.web.global.exception.exception.RestApiException;
import com.develop.web.global.exception.code.AuthErrorCode;
import lombok.extern.slf4j.Slf4j;
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
            throw new RestApiException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        } else if (access) {
            throw new RestApiException(AuthErrorCode.AUTH_ACCESS_NOT_FOUND);
            }
    }
}
