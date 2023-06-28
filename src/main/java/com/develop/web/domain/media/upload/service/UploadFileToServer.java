package com.develop.web.domain.media.upload.service;

import com.develop.web.domain.media.upload.dto.Metadata;
import com.develop.web.domain.media.upload.mapper.UploadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UploadFileToServer {

    @Value("${CNPS.MC.URL}")
    private String mc;
    private final UploadMapper uploadMapper;

    public WebClient webClient() {
        return WebClient
              .builder()
              .baseUrl(mc)
              .build();
    }

    public Metadata upload(Resource files, Integer ingestId) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", files);
        body.add("ingestId", ingestId);

        return webClient()
              .method(HttpMethod.POST)
              .uri("/api/upload/")
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.APPLICATION_JSON)
              .body(BodyInserters.fromMultipartData(body))
              .retrieve()
              .bodyToMono(Metadata.class)
              .doOnSuccess(uploadMapper::insertMetadata)
              .block();
    }
}
