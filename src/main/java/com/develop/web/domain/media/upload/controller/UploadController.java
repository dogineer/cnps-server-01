package com.develop.web.domain.media.upload.controller;

import com.develop.web.domain.media.ingest.service.CreateIngestPost;
import com.develop.web.domain.media.upload.dto.IngestRequestData;
import com.develop.web.domain.media.upload.service.FileChecker;
import com.develop.web.domain.media.upload.service.UploadFileToServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Tag(name = "아카이브 > 업로드", description = "아카이브 서버로 파일을 전송합니다.")
@Slf4j
@RequestMapping(value = "/archive")
public class UploadController {
    private final CreateIngestPost createIngestPost;
    private final UploadFileToServer uploadFileToServer;
    private final FileChecker fileChecker;

    @PostMapping(value = "/upload")
    @Operation(summary = "업로드", description = "영상을 업로드 합니다.")
    public String MediaUpload(IngestRequestData data){

        Resource mediaFiles = data.getFiles().getResource();

        try{
            fileChecker.fileNull(mediaFiles);
            uploadFileToServer.upload(mediaFiles);
        }catch (NullPointerException e){
            log.error(e.getMessage());
        }

        return "redirect:/home";
    }
}
