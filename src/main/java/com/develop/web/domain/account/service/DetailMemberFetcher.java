package com.develop.web.domain.account.service;

import com.develop.web.domain.account.dto.MemberInfo;
import com.develop.web.domain.auth.mapper.AuthMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailMemberFetcher {
    private final AuthMapper authMapper;

    public DetailMemberFetcher(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    public List<MemberInfo> getMember(String account) {
        return authMapper.selectMemberInfo(account);
    }
}
