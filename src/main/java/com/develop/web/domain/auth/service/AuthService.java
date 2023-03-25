package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;

public interface AuthService {
    boolean SignUpService(AuthVo authVo);

    AuthVo loginService(AuthVo authVo) throws Exception;
}
