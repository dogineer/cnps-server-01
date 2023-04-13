package com.develop.web.domain.auth.mapper;

import com.develop.web.domain.member.dto.Member;
import com.develop.web.domain.auth.dto.ViewInfoListMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {

    /** @description 멤버 데이터 인서트 (회원가입)*/
    void insertMemberInfo(Member member);

    /** @description 멤버 데이터 조회 (로그인)*/
    Member lookupMember(String account);

    /** @description 직원 비밀번호 업데이트 (비밀번호 변경)*/
    void changePassword(String account, String changePassword);

    /** @description 직원 리스트 데이터 조회 (직원 리스트) */
    List<ViewInfoListMember> queryListMember();

    /** @description 권한 승인 업데이트 (권한 승인)*/
    void joinMember(String account);

    /** @description 직원 삭제 업데이트 (직원 삭제 플래그)*/
    void deleteMember(String account);
}
