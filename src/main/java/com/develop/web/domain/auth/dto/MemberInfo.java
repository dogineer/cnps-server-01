package com.develop.web.domain.auth.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MemberInfo {
    private String account;
    private String name;
    private String rank;
    private String dept;
    private Date birth;
    private Byte gender;
    private String email;
    private String des;
    private LocalDateTime approved_at;
}
