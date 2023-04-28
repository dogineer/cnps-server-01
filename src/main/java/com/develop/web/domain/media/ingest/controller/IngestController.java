package com.develop.web.domain.media.ingest.controller;

import com.develop.web.domain.media.ingest.service.CreateIngestPost;
import com.develop.web.domain.media.ingest.service.IngestListFetcher;
import com.develop.web.domain.media.upload.dto.IngestRequestData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Tag(name = "영상 > 인제스트", description = "")
@Slf4j
@RequestMapping(value = "/media")
public class IngestController {
    private final IngestListFetcher ingestListFetcher;
    private final CreateIngestPost createIngestPost;

    @PostMapping(value = "/ingest/add")
    @Operation(summary = "인제스트", description = "영상을 업로드 합니다.")
    public void ingestRequset(IngestRequestData data,  HttpSession session){
        Integer memberId = session.getAttribute("empId").hashCode();
        data.setMemberId(memberId);
        createIngestPost.addIngestRequest(data);
    }

    @GetMapping(value = "/ingest/list")
    @Operation(summary = "인제스트 목록", description = "인제스트 목록 현황을 가져옵니다.")
    public void ingestList(){
        ingestListFetcher.getIngestRequestList();
    }
}
