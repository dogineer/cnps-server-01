package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateAccess {
    public final AuthMapper authMapper;

    public UpdateAccess(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public void setAccess(String account){
        authMapper.updateAccess(account);
    }
}
