package com.develop.web.domain.admin.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ANONYMOUS("ROLE_ANONYMOUS", "승인되지 않은 사용자"),
    ADMIN("ROLE_ADMIN", "어드민"),
    USER("ROLE_USER", "일반 사용자");

    private final String authority;
    private final String description;
}
