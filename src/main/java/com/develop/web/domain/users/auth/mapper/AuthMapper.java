package com.develop.web.domain.users.auth.mapper;

import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.users.token.dto.UserAuthDto;
import com.develop.web.domain.users.user.dto.JoinedUser;
import com.develop.web.domain.users.user.dto.Member;
import com.develop.web.domain.users.user.dto.Userinfo;
import com.develop.web.domain.users.user.dto.ProgramUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {

    /** @description 멤버 데이터 인서트 (회원가입)*/
    void insertUser(JoinedUser joinedUser);

    /** @description 멤버 데이터 카운트*/
    int selectEmpCount();

    /** @description 멤버 계정 아이디 조회*/
    String selectMemberAccount(String account);

    /** @description 멤버 데이터 조회 (로그인)*/
    Member selectMember(String account);

    /** @description 멤버 정보 조회 (내정보)*/
    Userinfo selectUserinfo(String account);

    /** @description 직원 비밀번호 업데이트 (비밀번호 변경)*/
    void updatePassword(String account, String changePassword);

    /** @description 직원 리스트 데이터 조회 (직원 리스트) */
    List<Userinfo> selectMemberInfoList();

    /** @description 직원 리스트 데이터 조회 (직원 리스트 - 가입날짜로 부터) */
    List<Userinfo> selectMemberGetList(CriteriaDto cri);

    /** @description 직원 탈퇴 리스트 데이터 조회 (직원 리스트 - 가입날짜로 부터) */
    List<Userinfo> selectMemberDeleteGetList(CriteriaDto cri);

    /** @description 직원 프로그램 배정 (직원 프로그램 업데이트,)*/
    void updateMemberProgramId(ProgramUpdateParam param);

    /** @description JWT를 위한 멤버 데이터 조회 (로그인)*/
    UserAuthDto selectJoinedMember(String account);
}
