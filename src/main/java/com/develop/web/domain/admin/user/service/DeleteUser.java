package com.develop.web.domain.admin.user.service;

import com.develop.web.domain.admin.user.mapper.AdminMapper;
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
