package com.develop.web.domain.ingest.service;

import com.develop.web.domain.ingest.dto.IngestRequestData;
import com.develop.web.domain.ingest.mapper.IngestMapper;
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
