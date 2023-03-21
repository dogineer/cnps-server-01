package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface AuthService {
    void SignUp(AuthVo authVo, HttpServletResponse response);

    AuthVo login(AuthVo authVo, HttpSession session);
}
