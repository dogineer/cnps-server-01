package com.develop.web.domain.media.upload.dto;

import lombok.Data;

@Data
public class Metadata {
    public String clip_uuid;
    public String file_path;
    public String filename;
    public String ext;
    public int width;
    public int height;
    public String format_name;
    public String format_long_name;
    public String tags;
    public double duration;
    public Long size;
}
