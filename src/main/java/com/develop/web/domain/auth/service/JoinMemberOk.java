package com.develop.web.domain.auth.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

@Service
public class JoinMemberOk {
    private final AuthMapper authMapper;

    public JoinMemberOk(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    /** @description 가입 승인 */
    public void ok(String account){
        authMapper.updateAccess(account);
    }

    /** @description 가입 떠남 */
    public void leave (String account) {
        authMapper.deleteMember(account);
    }
}
