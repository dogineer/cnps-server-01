package com.develop.web.domain.users.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenRole {
    SUPER_ADMIN("0","SUPER_ADMIN"),
    ADMIN("ADMIN", "ADMIN"),
    USER("2", "USER")
    ;

    private final String key;
    private final String tag;
}