package com.develop.web.domain.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
public class User {

    private String userid;              // 유저 아이디
    private String password;            // 패스워드
    private String name;
    private String verify_password;     // 패스워드 확인
    private String passwordChangeData;  // 패스워드 변경
    private Role role;                  // 역할

    private String phone;               // 전화번호
    private String email;               // 이메일
    private String department;          // 부서
    private String description;         // 신청 설명(사유)
    private LocalDateTime joinDate;    // 가입 날짜

    private int delete_flag;            // 유저 삭제 플래그

    private Access access;         // 가입 승인 요청
}
