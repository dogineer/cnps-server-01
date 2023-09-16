package com.develop.web.domain.service.ingest.dto;

import lombok.Data;

@Data
public class Metadata {
    public Integer id;
    public String clip_uuid;
    public String file_path;
    public String file_name;
    public String file_ext;
    public int width;
    public int height;
    public String format_name;
    public String format_long_name;
    public String tags;
    public double file_duration;
    public Long file_size;
}
