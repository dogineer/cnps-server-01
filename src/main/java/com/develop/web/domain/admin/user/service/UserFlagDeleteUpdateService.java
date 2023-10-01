package com.develop.web.domain.admin.user.service;

import com.develop.web.domain.admin.user.mapper.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFlagDeleteUpdateService {
    private final AdminUserMapper adminUserMapper;

    public void modifyUserDeleteFlag(String account){
        adminUserMapper.updateUserDeleteFlag(account);
    }
}
