package com.develop.web.domain.ingest.mapper;

import com.develop.web.domain.ingest.dto.IngestRequestData;
import com.develop.web.domain.ingest.dto.Metadata;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IngestMapper {
    void insertIngestRequest(IngestRequestData vo);

    void insertMetadata(Metadata data);
}
