package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;

import java.util.List;

public interface AuthService {
    boolean SignUpService(AuthVo authVo);

    boolean changePassword(AuthVo authVo);

    AuthVo loginService(AuthVo authVo) throws Exception;

    List<AuthVo> memberlistAll();

    void accessCheck(AuthVo vo);

    void deleteUser(AuthVo vo);
}
