package com.develop.web.domain.admin.service;

import com.develop.web.domain.admin.mapper.AdminMapper;
import com.develop.web.domain.auth.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUser {
    private final AdminMapper adminMapper;

    public void updateUserDeleteFlag(String account){
        adminMapper.updateUserDeleteFlag(account);
    }
}
