package com.develop.web.domain.media.ingest.mapper;

import com.develop.web.domain.media.ingest.dto.IngestRequestData;
import com.develop.web.domain.media.ingest.dto.Metadata;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IngestMapper {
    void insertIngestRequest(IngestRequestData vo);

    void insertMetadata(Metadata data);

    List<IngestRequestData> selectIngestList();
}
