package com.develop.web.domain.users.user.service;

import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.domain.users.user.dto.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailMemberFetcher {
    private final AuthMapper authMapper;

    public MemberInfo getMember(String account) {
        return authMapper.selectMemberInfo(account);
    }
}
