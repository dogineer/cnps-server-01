package com.develop.web.domain.media.upload.mapper;

import com.develop.web.domain.media.upload.dto.IngestRequestData;
import com.develop.web.domain.media.upload.dto.Metadata;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IngestMapper {
    void insertIngestRequest(IngestRequestData vo);

    void insertMetadata(Metadata data);

    List<IngestRequestData> selectIngestList();
}
