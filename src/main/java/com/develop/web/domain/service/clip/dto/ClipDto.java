package com.develop.web.domain.service.clip.dto;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class ClipDto {
    public Integer clip_id;
    public Integer program_id;
    public String program_name;
    public String folder_name;

    public String clip_uuid;
    public String file_name;
    public String file_ext;
    public String file_path;

    public String format_long_name;
    public double file_duration;
    public double file_size;
    public Date ingest_at;
    public Date end_at;

    public Byte del_fl;
}
