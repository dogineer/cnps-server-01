package com.develop.web.domain.media.upload.controller;

import com.develop.web.domain.media.ingest.service.CreateClipPost;
import com.develop.web.domain.media.upload.service.CreateFileFromMultipartFile;
import com.develop.web.domain.media.upload.service.FileChecker;
import com.develop.web.domain.media.upload.service.UploadFileToServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Tag(name = "아카이브 > 업로드", description = "아카이브 서버로 파일을 전송합니다.")
@Slf4j
@RequestMapping(value = "/archive")
public class UploadController {
    private final UploadFileToServer uploadFileToServer;
    private final FileChecker fileChecker;
    private final CreateClipPost createClipPost;
    private final CreateFileFromMultipartFile createFileFromMultipartFile;

    @Value("${app.upload.dir:${user.home}/movies/mam/temp/}")
    private String TempDir;

    @PostMapping(value = "/upload")
    @Operation(summary = "원본 업로드", description = "영상 변환을 거치지 않고 영상을 업로드 합니다.")
    public void MediaUpload(ClipPostDto clipPostDto, HttpSession session) throws IOException {
        Resource mediaFiles = createFileFromMultipartFile.run(clipPostDto.getFiles(), TempDir);

        try {
            fileChecker.fileNull(mediaFiles);


        } catch (NullPointerException e) {
            log.error(e.getMessage());
        }
    }
}
