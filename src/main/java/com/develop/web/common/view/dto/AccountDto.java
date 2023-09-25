package com.develop.web.common.view.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountDto {
    private final String account;
    private final Integer programId;
    private final Integer rankId;
    private final boolean isAdmin;
}
