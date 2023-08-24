package com.develop.web.domain.service.ingest.service;

import com.develop.web.domain.service.ingest.dto.IngestRequestData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileCheckerService {
    public void isFileNull(IngestRequestData ingestRequestData) {
        if (ingestRequestData.getFiles() == null) {
            log.error("영상이 없습니다. IngestRequestData: {}", ingestRequestData.toString());
        }
    }
}
