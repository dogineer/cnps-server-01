package com.develop.web.domain.personnel.account.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MemberInfo {
    private String account;
    private String name;
    private String rankName;
    private String deptName;
    private String teamName;
    private Integer rankId;
    private Integer deptId;
    private Integer teamId;
    private String birth;
    private Byte gender;
    private String email;
    private String des;
    private Byte access;
    private Byte del_fl;
    private LocalDateTime joined_at;
    private LocalDateTime approved_at;
}
