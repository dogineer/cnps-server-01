package com.develop.web.domain.media.ingest.service;

import com.develop.web.domain.media.upload.mapper.UploadMapper;
import com.develop.web.domain.media.ingest.dto.IngestRequestData;
import org.springframework.stereotype.Service;

@Service
public class CreateIngestPost {

    private final UploadMapper uploadMapper;

    public CreateIngestPost(UploadMapper uploadMapper) {
        this.uploadMapper = uploadMapper;
    }

    /** @description 인제스트 요청 글 작성 */
    public void addIngestRequest(IngestRequestData requestData) {
        uploadMapper.insertIngestRequest(requestData);
    }
}
