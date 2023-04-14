package com.develop.web.domain.auth.mapper;

import com.develop.web.domain.account.dto.Member;
import com.develop.web.domain.auth.dto.MemberInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {

    /** @description 멤버 데이터 인서트 (회원가입)*/
    void insertMember(Member member);

    /** @description 멤버 데이터 조회 (로그인)*/
    Member selectMember(String account);

    /** @description 직원 비밀번호 업데이트 (비밀번호 변경)*/
    void updatePassword(String account, String changePassword);

    /** @description 직원 리스트 데이터 조회 (직원 리스트) */
    List<MemberInfo> selectMemberInfoList();

    /** @description 권한 승인 업데이트 (권한 승인)*/
    void updateAccess(String account);

    /** @description 직원 삭제 (삭제 플래그)*/
    void deleteMember(String account);
}
