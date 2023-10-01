package com.develop.web.domain.admin.user.service;

import com.develop.web.domain.admin.user.dto.UpdateUserInfoDto;
import com.develop.web.domain.admin.user.mapper.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserinfoUpdateService {
    public final AdminUserMapper adminUserMapper;

    public void modifyUserInfo(UpdateUserInfoDto userInfo){
        adminUserMapper.updateUserInfo(userInfo);
    }
}
