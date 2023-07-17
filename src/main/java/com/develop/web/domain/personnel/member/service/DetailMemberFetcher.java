package com.develop.web.domain.personnel.member.service;

import com.develop.web.domain.auth.mapper.AuthMapper;
import com.develop.web.domain.personnel.member.dto.MemberInfo;
import org.springframework.stereotype.Service;

@Service
public class DetailMemberFetcher {
    private final AuthMapper authMapper;

    public DetailMemberFetcher(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public MemberInfo getMember(String account) {
        return authMapper.selectMemberInfo(account);
    }
}
