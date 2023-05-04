package com.develop.web.domain.media.ingest.controller;

import com.develop.web.domain.media.ingest.dto.ResultRequestData;
import com.develop.web.domain.media.ingest.service.CreateClipPost;
import com.develop.web.domain.media.ingest.service.CreateIngestPost;
import com.develop.web.domain.media.ingest.service.IngestListFetcher;
import com.develop.web.domain.media.ingest.dto.IngestRequestData;
import com.develop.web.domain.media.upload.service.FileChecker;
import com.develop.web.domain.media.upload.service.UploadFileToServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
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
    private final UploadFileToServer uploadFileToServer;
    private final FileChecker fileChecker;
    private final CreateClipPost createClipPost;

    @PostMapping(value = "/ingest/add")
    @Operation(summary = "인제스트", description = "인제스트를 추가합니다.")
    public void ingestRequset(IngestRequestData ingestRequestData,  HttpSession session){
        Integer memberId = session.getAttribute("empId").hashCode();

        ingestRequestData.setMemberId(memberId);
        createIngestPost.addIngestRequest(ingestRequestData);

        Resource mediaFiles = ingestRequestData.getFiles().getResource();

        try{
            fileChecker.fileNull(mediaFiles);
            uploadFileToServer.upload(mediaFiles);

            ResultRequestData requestData = new ResultRequestData();

            requestData.ingest_id = 1;
            requestData.team_id = 1;
            requestData.folder_id = 1;
            requestData.e_metadata_id = 1;
            requestData.a_metadata_id = 1;

            createClipPost.addClipPost(requestData);

        }catch (NullPointerException e){
            log.error(e.getMessage());
        }
    }

    @GetMapping(value = "/ingest/list")
    @Operation(summary = "인제스트 목록", description = "인제스트 목록 현황을 가져옵니다.")
    public void ingestList(){
        ingestListFetcher.getIngestRequestList();
    }
}
