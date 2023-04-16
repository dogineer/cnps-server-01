package com.develop.web.domain.account.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

@Service
public class DeleteMember {
    private final AuthMapper authMapper;

    public DeleteMember(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public void updateMemberDeleteFlag(String account){
        authMapper.updateMemberDeleteFlag(account);
    }
}
