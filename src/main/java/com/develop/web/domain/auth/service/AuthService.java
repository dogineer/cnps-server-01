package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;

public interface AuthService {
    boolean SignUpService(AuthVo authVo);

    boolean changePassword(AuthVo authVo);

    boolean loginService(AuthVo authVo) throws Exception;
}
