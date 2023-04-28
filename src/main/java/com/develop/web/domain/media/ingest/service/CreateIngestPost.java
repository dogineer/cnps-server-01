package com.develop.web.domain.media.ingest.service;

import com.develop.web.domain.media.upload.mapper.IngestMapper;
import com.develop.web.domain.media.upload.dto.IngestRequestData;
import org.springframework.stereotype.Service;

@Service
public class CreateIngestPost {

    private final IngestMapper ingestMapper;

    public CreateIngestPost(IngestMapper ingestMapper) {
        this.ingestMapper = ingestMapper;
    }

    /** @description 인제스트 요청 글 작성 */
    public void addIngestRequest(IngestRequestData requestData) {

        ingestMapper.insertIngestRequest(requestData); // 글 작성
    }
}
