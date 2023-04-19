package com.develop.web.domain.ingest.mapper;

import com.develop.web.domain.ingest.dto.IngestRequestData;
import com.develop.web.domain.ingest.dto.Metadata;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IngestMapper {
    void insertIngestRequest(IngestRequestData vo);

    void insertMetadata(Metadata data);

    List<IngestRequestData> selectIngestList();
}
