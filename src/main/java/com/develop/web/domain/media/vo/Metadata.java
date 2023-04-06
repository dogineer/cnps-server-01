package com.develop.web.domain.media.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Metadata {
    private LocalDateTime createDate;   // 생성 날짜
    public String filename;
    public int width;
    public int height;
    public String format_name;
    public String format_long_name;
    public String tags;
    public double duration;
    public Long size;
}
