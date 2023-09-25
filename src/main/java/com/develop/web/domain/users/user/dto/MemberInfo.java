package com.develop.web.domain.users.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberInfo {
    private Integer id;
    private String account;
    private String name;
    private Integer rankId;
    private String rankName;
    private Integer deptId;
    private String deptName;
    private Integer programId;
    private String programName;
    private String birth;
    private Byte gender;
    private String phone;
    private String email;
    private String des;
    private Byte access;
    private Byte del_fl;
    private LocalDateTime joined_at;
    private LocalDateTime approved_at;
}
