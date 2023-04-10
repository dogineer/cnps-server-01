package com.develop.web.domain.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;                    // 유저 PK
    private String account;             // 유저 아이디
    private String password;            // 패스워드
    private String name;                // 이름
    private String verifyPassword;      // 패스워드 확인
    private String changePassword;      // 패스워드 변경
    private String dep;          // 부서
    private String team;                // 배정팀
    private Role role;                  // 역할

    private String phone;               // 전화번호
    private String email;               // 이메일
    private String des;                 // 신청 설명(사유)
    private LocalDateTime joined_At;     // 가입 날짜

    private int access;                 // 가입 승인 요청 플래그
    private int delFlag;                 // 유저 삭제 플래그
}
