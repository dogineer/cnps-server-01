package com.develop.web.domain.service.ingest.service;

import com.develop.web.domain.service.ingest.dto.IngestListDto;
import com.develop.web.domain.service.ingest.dto.IngestRequestData;
import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.service.ingest.mapper.UploadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngestListFetcher {
    private final UploadMapper uploadMapper;

    public List<IngestListDto> getIngestRequestList(CriteriaDto cri) {
        return uploadMapper.selectGetIngestList(cri);
    }
}
