package com.develop.web.domain.users.user.service;

import com.develop.web.domain.users.auth.mapper.AuthMapper;
import com.develop.web.domain.users.user.dto.Userinfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailFetcherService {
    private final AuthMapper authMapper;

    public Userinfo findMember(String account) {
        return authMapper.selectUserinfo(account);
    }
}
