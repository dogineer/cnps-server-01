package com.develop.web.domain.media.ingest.service;

import com.develop.web.domain.media.ingest.dto.IngestRequestData;
import com.develop.web.domain.page.dto.CriteriaDto;
import com.develop.web.domain.media.upload.mapper.UploadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngestListFetcher {
    private final UploadMapper uploadMapper;

    public List<IngestRequestData> getIngestRequestList(CriteriaDto cri) {
        return uploadMapper.selectGetIngestList(cri);
    }
}
