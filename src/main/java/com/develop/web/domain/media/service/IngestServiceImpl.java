package com.develop.web.domain.media.service;

import com.develop.web.domain.media.mapper.IngestMapper;
import com.develop.web.domain.media.vo.IngestRequestData;
import com.develop.web.domain.media.vo.Metadata;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

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

        MultipartFile file = requestData.getFiles();

        Metadata data =  webClient()
                .method(HttpMethod.GET)
                .uri("/api/upload/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData("files", file.getResource()))
                .retrieve()
                .bodyToMono(Metadata.class)
                .block();

        ingestMapper.insertMetadata(data);
    }
}
