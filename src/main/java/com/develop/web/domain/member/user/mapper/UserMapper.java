package com.develop.web.domain.member.user.mapper;

import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.member.token.dto.UserAuthDto;
import com.develop.web.domain.member.user.dto.JoinUser;
import com.develop.web.domain.member.user.dto.UserDto;
import com.develop.web.domain.member.user.dto.Userinfo;
import com.develop.web.domain.member.user.dto.ProgramUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /** @description 유저 데이터 인서트 (회원가입)*/
    void insertUser(JoinUser joinUser);

    /** @description 유저 비밀번호 업데이트 (비밀번호 변경)*/
    void updateUserPassword(String account, String changePassword);

    /** @description 유저 프로그램 배정 (직원 프로그램 업데이트,)*/
    void updateUserProgram(ProgramUpdateParam param);

    /** @description 유저 데이터 개수 카운트*/
    int selectUserCount();

    /** @description 유저 계정 아이디 조회*/
    String selectUserAccount(String account);

    /** @description 유저 데이터 조회 (로그인)*/
    UserDto selectUser(String account);

    /** @description 유저 정보 조회 (내정보)*/
    Userinfo selectUserinfo(String account);

    /** @description 유저 리스트 데이터 조회 (직원 리스트) */
    List<Userinfo> selectMemberInfoList();

    /** @description 유저 리스트 데이터 조회 (직원 리스트 - 가입날짜로 부터) */
    List<Userinfo> selectMemberGetList(CriteriaDto cri);

    /** @description 유저 탈퇴 리스트 데이터 조회 (직원 리스트 - 가입날짜로 부터) */
    List<Userinfo> selectMemberDeleteGetList(CriteriaDto cri);

    /** @description JWT를 위한 멤버 데이터 조회 (로그인)*/
    UserAuthDto selectJoinedMember(String account);
}
