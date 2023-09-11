package com.develop.web.domain.service.folder.dto;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class FolderClipDto {
    public Integer clip_id;
    public Integer ingest_id;
    public Integer team_id;
    public Integer folder_id;
    public Integer archive_metadata_id;

    public String ingest_name;
    public String team_name;
    public String folder_name;

    public Integer id;
    public String clip_uuid;
    public String file_path;
    public String file_name;
    public String ext;
    public int width;
    public int height;
    public String format_name;
    public String format_long_name;
    public String tags;
    public double duration;
    public Long size;

    public Date ingest_at;
    public LocalDateTime end_at;
    public Byte del_fl;
}
