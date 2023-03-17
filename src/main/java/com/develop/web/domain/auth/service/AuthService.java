package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.vo.AuthVo;

public interface AuthService {
    void SignUp(AuthVo authVo);

    AuthVo login(AuthVo authVo);
}
