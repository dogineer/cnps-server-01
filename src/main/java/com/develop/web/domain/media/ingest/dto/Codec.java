package com.develop.web.domain.media.ingest.dto;

public enum Codec {
    XDCAMHD422("XDCAMHD422"),
    ProResLT("ProResLT");

    private String description;

    Codec(String description) {
        this.description = description;
    }
}