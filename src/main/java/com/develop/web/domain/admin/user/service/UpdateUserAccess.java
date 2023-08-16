package com.develop.web.domain.admin.user.service;

import com.develop.web.domain.admin.user.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserAccess {
    public final AdminMapper adminMapper;

    public void setAccess(String account){
        adminMapper.updateAccess(account);
    }
}
