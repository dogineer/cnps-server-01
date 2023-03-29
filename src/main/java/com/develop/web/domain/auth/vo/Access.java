package com.develop.web.domain.auth.vo;

public enum Access {

    allow("허용"),
    deny("거절");

    private String description;

    Access(String description) {
        this.description = description;
    }

}