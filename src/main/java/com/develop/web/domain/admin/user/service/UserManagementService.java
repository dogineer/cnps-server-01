package com.develop.web.domain.admin.user.service;

import com.develop.web.domain.admin.user.dto.UpdateUserInfoDto;
import com.develop.web.domain.admin.user.mapper.AdminUserMapper;
import com.develop.web.domain.member.user.mapper.UserMapper;
import com.develop.web.domain.member.user.dto.Userinfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementService {
    public final AdminUserMapper adminUserMapper;
    public final UserMapper userMapper;

    /** @description 유저 접근 권한 수정 (승인) */
    public void modifyAccess(String account) {
        adminUserMapper.updateAccess(account);
    }

    /** @description 유저 삭제 플래그 */
    public void modifyUserDeleteFlag(String account) {
        adminUserMapper.updateUserDeleteFlag(account);
    }

    /** @description 유저 찾기 */
    public Userinfo findMember(String account) {
        return userMapper.selectUserinfo(account);
    }

    /** @description 유저 완전 소거 */
    public void deleteUser(String account) {
        adminUserMapper.deleteUser(account);
    }

    /** @description 유저 정보 수정 */
    public void modifyUserInfo(UpdateUserInfoDto userInfo) {
        adminUserMapper.updateUserInfo(userInfo);
    }
}
