package com.develop.web.domain.account.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MemberInfo {
    private String account;
    private String name;
    private String rankId;
    private String deptId;
    private LocalDateTime birth;
    private Byte gender;
    private String email;
    private String des;
    private Byte access;
    private LocalDateTime approved_at;
}
