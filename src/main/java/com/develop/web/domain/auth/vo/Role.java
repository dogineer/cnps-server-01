package com.develop.web.domain.auth.vo;

public enum Role {

    ROLE_ADMIN("관리자"),
    ROLE_USER("일반사용자");

    private String description;

    Role(String description) {
        this.description = description;
    }

}
