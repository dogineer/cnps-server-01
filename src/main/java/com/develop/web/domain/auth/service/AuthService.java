package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface AuthService {
    boolean SignUp(AuthVo authVo, HttpServletResponse response);

    boolean login(AuthVo authVo, HttpSession session) throws Exception;
}
