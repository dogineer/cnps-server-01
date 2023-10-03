package com.develop.web.domain.admin.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserInfoDto {
    private String account;
    private String name;
    private String phone;
    private Integer posId;
    private String role;
    private Integer deptId;
    private Integer programId;
    private Byte gender;
    private String email;
}
