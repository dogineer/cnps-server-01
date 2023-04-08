package com.develop.web.domain.media.service;

import com.develop.web.domain.media.mapper.IngestMapper;
import com.develop.web.domain.media.vo.IngestRequestData;
import com.develop.web.domain.media.vo.Metadata;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class IngestServiceImpl implements IngestService{

    private final IngestMapper ingestMapper;

    public IngestServiceImpl(IngestMapper ingestMapper) {
        this.ingestMapper = ingestMapper;
    }

    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl("http://localhost:8081")
                .build();

    }

    /**
     * @description 인제스트 요청
     * @param requestData IngestRequestData 객체의 필드값
     * */

    @Override
    public void IngestRequest(IngestRequestData requestData) {

        ingestMapper.insertIngestRequest(requestData);

        Resource file = requestData.getFiles().getResource();

        webClient()
                .method(HttpMethod.GET)
                .uri("/api/upload/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData("files", file))
                .retrieve()
                .bodyToMono(Metadata.class)
                .doOnSuccess(ingestMapper::insertMetadata)
                .subscribe();
    }
}
