package com.develop.web.domain.service.ingest.service;

import com.develop.web.domain.service.ingest.dto.IngestRequestData;
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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ServerFileUploadService {

    @Value("${CNPS.MC.URL}")
    private String mc;
    public WebClient webClient() {
        return WebClient
            .builder()
            .baseUrl(mc)
            .build();
    }

    public void IngestRequestData(Resource files, IngestRequestData ingestRequestData) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", files);
        body.add("memberId", ingestRequestData.getMemberId());
        body.add("ingestId", ingestRequestData.getId());
        body.add("programId", ingestRequestData.getProgramId());
        body.add("folderId", ingestRequestData.getFolder());
        body.add("ingestAt", LocalDateTime.now());

        webClient()
            .method(HttpMethod.POST)
            .uri("/s2/api/upload")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(body))
            .retrieve()
            .bodyToMono(void.class)
            .subscribe();
    }
}
