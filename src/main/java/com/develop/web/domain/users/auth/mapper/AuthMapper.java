package com.develop.web.domain.users.auth.mapper;

import com.develop.web.domain.service.page.dto.CriteriaDto;
import com.develop.web.domain.users.user.dto.JoinedMember;
import com.develop.web.domain.users.user.dto.Member;
import com.develop.web.domain.users.user.dto.MemberInfo;
import com.develop.web.domain.users.user.dto.TeamUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {

    /** @description 멤버 데이터 인서트 (회원가입)*/
    void insertMember(JoinedMember member);

    /** @description 멤버 데이터 카운트*/
    int selectEmpCount();

    /** @description 멤버 계정 아이디 조회*/
    String selectMemberAccount(String account);

    /** @description 멤버 데이터 조회 (로그인)*/
    Member selectMember(String account);

    /** @description 멤버 정보 조회 (내정보)*/
    MemberInfo selectMemberInfo(String account);

    /** @description 직원 비밀번호 업데이트 (비밀번호 변경)*/
    void updatePassword(String account, String changePassword);

    /** @description 직원 리스트 데이터 조회 (직원 리스트) */
    List<MemberInfo> selectMemberInfoList();

    /** @description 직원 리스트 데이터 조회 (직원 리스트 - 가입날짜로 부터) */
    List<MemberInfo> selectMemberGetList(CriteriaDto cri);

    /** @description 직원 팀 배정 (직원 팀 업데이트,)*/
    void updateMemberTeamId(TeamUpdateParam param);
}
