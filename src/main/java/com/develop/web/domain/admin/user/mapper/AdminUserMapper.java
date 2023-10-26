package com.develop.web.domain.admin.user.mapper;

import com.develop.web.domain.admin.user.dto.UpdateUserInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper {

    /** @description 권한 승인 업데이트 (권한 승인 및 승인 날짜 추가)*/
    void updateAccess(String account);

    /** @description 유저 삭제 (삭제 플래그)*/
    void updateUserDeleteFlag(String account);

    /** @description 유저 완전 소거*/
    void deleteUser(String account);

    /** @description 유저 정보 수정*/
    void updateUserInfo(UpdateUserInfoDto userInfo);
}
