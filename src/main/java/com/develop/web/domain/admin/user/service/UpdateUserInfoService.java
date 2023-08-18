package com.develop.web.domain.admin.user.service;

import com.develop.web.domain.admin.user.dto.UpdateUserInfoDto;
import com.develop.web.domain.admin.user.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoService {
    public final AdminMapper adminMapper;

    public void updateUserInfo(UpdateUserInfoDto userInfo){
        adminMapper.updateUserInfo(userInfo);
    }
}
