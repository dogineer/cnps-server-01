package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateApprovedAt {
    private final AuthMapper authMapper;

    public UpdateApprovedAt(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public void setApprovedAt(String account){
        authMapper.updateApprovedAt(account);
    }
}
