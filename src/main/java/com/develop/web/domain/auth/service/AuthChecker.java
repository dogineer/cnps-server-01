package com.develop.web.domain.auth.service;

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

        boolean account = session.getAttribute("account") == null; //false
        boolean access = session.getAttribute("access") == null; //ture

        if (account && access){
            throw new NullPointerException();
        }
    }
}
