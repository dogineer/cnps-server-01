package com.develop.web.domain.admin.user.service;

import com.develop.web.domain.admin.user.mapper.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccessUpdateService {
    public final AdminUserMapper adminUserMapper;

    public void modifyAccess(String account){
        adminUserMapper.updateAccess(account);
    }
}
