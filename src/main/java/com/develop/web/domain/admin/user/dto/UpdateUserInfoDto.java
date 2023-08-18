package com.develop.web.domain.admin.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserInfoDto {
    private String account;
    private String name;
    private String phone;
    private Integer rank;
    private Integer dept;
    private Integer team;
    private Byte gender;
    private String email;
}
