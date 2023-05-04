package com.develop.web.domain.media.ingest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResultRequestData {
    public Integer id;
    public Integer ingest_id;
    public Integer team_id;
    public Integer folder_id;
    public Integer e_metadata_id;
    public Integer a_metadata_id;
    public LocalDateTime ingest_at;
    public LocalDateTime end_at;
    public Byte del_fl;
}
