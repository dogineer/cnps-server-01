package com.develop.web.domain.service.ingest.service;

import com.develop.web.domain.service.ingest.dto.IngestRequestData;
import com.develop.web.domain.service.page.dto.CriteriaDto;
import com.develop.web.domain.service.upload.mapper.UploadMapper;
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
