package com.develop.web.domain.admin.user.service;

import com.develop.web.domain.admin.user.mapper.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteService {
    private final AdminUserMapper adminUserMapper;

    public void deleteUser(String account){
        adminUserMapper.deleteUser(account);
    }
}
