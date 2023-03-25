package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;

import javax.servlet.http.HttpSession;

public interface AuthService {
    boolean SignUpService(AuthVo authVo);

    AuthVo loginService(AuthVo authVo) throws Exception;

    String redirectPage(String url, HttpSession session);
}
