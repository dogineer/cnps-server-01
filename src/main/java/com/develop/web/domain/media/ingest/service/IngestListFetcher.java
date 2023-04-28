package com.develop.web.domain.media.ingest.service;

import com.develop.web.domain.media.upload.dto.IngestRequestData;
import com.develop.web.domain.media.upload.mapper.IngestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngestListFetcher {
    private final IngestMapper ingestMapper;

    public List<IngestRequestData> getIngestRequestList() {
        return ingestMapper.selectIngestList();
    }
}
