package com.develop.web.domain.media.ingest.service;

import com.develop.web.domain.media.ingest.dto.Metadata;
import com.develop.web.domain.media.ingest.mapper.IngestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UploadFileToServer {

    private final IngestMapper ingestMapper;

    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl("http://localhost:8081")
                .build();
        }

    public void upload(Resource file){
        webClient()
            .method(HttpMethod.GET)
            .uri("/api/upload/")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromMultipartData("files", file))
            .retrieve()
            .bodyToMono(Metadata.class)
            .doOnSuccess(ingestMapper::insertMetadata)
            .block();
    }
}
