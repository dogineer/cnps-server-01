package com.develop.web.domain.media.upload.mapper;

import com.develop.web.domain.media.ingest.dto.IngestRequestData;
import com.develop.web.domain.page.dto.CriteriaDto;
import com.develop.web.domain.media.upload.dto.Metadata;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadMapper {
    void insertIngestRequest(IngestRequestData vo);

    void insertMetadata(Metadata data);

    List<IngestRequestData> selectIngestList();

    List<IngestRequestData> selectGetIngestList(CriteriaDto cri);

    int selectIngestCount();
}
