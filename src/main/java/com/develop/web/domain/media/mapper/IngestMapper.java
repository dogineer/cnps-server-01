package com.develop.web.domain.media.mapper;

import com.develop.web.domain.media.vo.IngestRequestData;
import com.develop.web.domain.media.vo.Metadata;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IngestMapper {
    void insertIngestRequest(IngestRequestData vo);

    void insertMetadata(Metadata data);
}
