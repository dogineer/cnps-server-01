package com.develop.web.domain.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    /** @description 권한 승인 업데이트 (권한 승인 및 승인 날짜 추가)*/
    void updateAccess(String account);

    /** @description 직원 삭제 (삭제 플래그)*/
    void updateUserDeleteFlag(String account);
}
