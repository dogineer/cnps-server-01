package com.develop.web.domain.account.service;

import com.develop.web.domain.account.dto.Member;
import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

@Service
public class DetailMemberFetcher {
    private final AuthMapper authMapper;

    public DetailMemberFetcher(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public Member getMember(String account) {
        return authMapper.selectMember(account);
    }
}
