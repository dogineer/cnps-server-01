package com.develop.web.domain.users.auth.service;

import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class AuthChecker {
    /**
     * @description 멤버 권한 체크 서비스
     * */
    public void blockOutsiders(HttpSession session) throws CustomException {

        if (session.getAttribute("account") == null){
            throw new CustomException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        } else if (session.getAttribute("access").hashCode() == 0) {
            throw new CustomException(AuthErrorCode.AUTH_ACCESS_NOT_FOUND);
            }
    }
}
