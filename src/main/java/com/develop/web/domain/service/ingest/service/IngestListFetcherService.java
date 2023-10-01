package com.develop.web.domain.service.ingest.service;

import com.develop.web.domain.service.ingest.dto.IngestListDto;
import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.service.ingest.mapper.UploadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngestListFetcherService {
    private final UploadMapper uploadMapper;

    public List<IngestListDto> findIngestRequestList(CriteriaDto cri) {
        return uploadMapper.selectGetIngestList(cri);
    }
}
