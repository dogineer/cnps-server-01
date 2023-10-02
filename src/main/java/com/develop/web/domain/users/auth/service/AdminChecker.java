package com.develop.web.domain.users.auth.service;

import com.develop.web.global.exception.code.AuthErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Service
public class AdminChecker {
    /**=
     * @description 멤버 권한 체크 서비스
     * */
    public void rankPermissionCheck(HttpSession session){
        if (!Objects.equals(session.getAttribute("pos").toString(), "12")){
            throw new CustomException(AuthErrorCode.NO_ACCESS);
        }
    }
}
