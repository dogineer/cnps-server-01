package com.develop.web.domain.media.ingest.controller;

import com.develop.web.domain.media.ingest.service.CreateIngestPost;
import com.develop.web.domain.media.ingest.dto.IngestRequestData;
import com.develop.web.domain.media.ingest.service.FileChecker;
import com.develop.web.domain.media.ingest.service.UploadFileToServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/ingest")
public class IngestController {
    private final CreateIngestPost createIngestPost;
    private final UploadFileToServer uploadFileToServer;
    private final FileChecker fileChecker;

    @PostMapping(value = "/upload")
    public String ingestRequest(IngestRequestData data, HttpSession session){
        Integer memberId = session.getAttribute("empId").hashCode();
        Resource mediaFiles = data.getFiles().getResource();

        try{
            fileChecker.fileNull(mediaFiles);
            data.setMemberId(memberId);
            createIngestPost.addIngestRequest(data);
            uploadFileToServer.upload(mediaFiles);
        }catch (NullPointerException e){
            log.error(e.getMessage());
        }

        return "redirect:/home";
    }
}
