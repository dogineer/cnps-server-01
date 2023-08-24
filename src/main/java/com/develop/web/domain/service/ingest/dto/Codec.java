package com.develop.web.domain.service.ingest.dto;

public enum Codec {
    XDCAMHD422("XDCAMHD422"),
    ProResLT("ProResLT"),
    H264("libx264"),
    DNxHD("dnxhd"),
    ;

    private String id;

    Codec(String id) {
        this.id = id;
    }
}