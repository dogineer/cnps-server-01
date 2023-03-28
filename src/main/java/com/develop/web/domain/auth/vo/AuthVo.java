package com.develop.web.domain.auth.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthVo {

    private String userid;              // 유저 아이디
    private String password;            // 패스워드
    private String verify_password;     // 패스워드 확인
    private String passwordChangeData;  // 패스워드 변경
    private Role role;                  // 역할

    private String phone_number;        // 전화번호
    private String email;               // 이메일
    private String department;          // 부서
    private String description;         // 신청 설명(사유)
    private Date join_data;             // 가입 날짜

    public AuthVo() {
    }

    public AuthVo(String password, String passwordChangeData) {
        this.password = password;
        this.passwordChangeData = passwordChangeData;
    }

    public AuthVo(String userid, String password, String passwordChangeData) {
        this.userid = userid;
        this.password = password;
        this.passwordChangeData = passwordChangeData;
    }

    public AuthVo(String userid, String password, Role role) {
        this.userid = userid;
        this.password = password;
        this.role = role;
    }
}
