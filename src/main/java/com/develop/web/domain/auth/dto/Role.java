package com.develop.web.domain.auth.dto;

public enum Role {

    Administrator("최고 관리자"),
    NPS_ADMIN("NPS 관리자"),
    USER("일반사용자"),
    PD("PD"),
    AD("연출 보조자"),
    FD("조연출");

    private String description;

    Role(String description) {
        this.description = description;
    }

}
