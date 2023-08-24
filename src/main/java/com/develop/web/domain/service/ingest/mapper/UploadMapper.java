package com.develop.web.domain.service.ingest.mapper;

import com.develop.web.domain.service.ingest.dto.IngestRequestData;
import com.develop.web.domain.service.page.dto.CriteriaDto;
import com.develop.web.domain.service.ingest.dto.Metadata;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadMapper {
    void insertIngestRequest(IngestRequestData vo);

    void insertMetadata(Metadata data);

    List<IngestRequestData> selectGetIngestList(CriteriaDto cri);

    int selectIngestCount();
}
