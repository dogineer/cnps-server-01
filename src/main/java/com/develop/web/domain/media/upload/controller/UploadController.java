package com.develop.web.domain.media.upload.controller;

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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Tag(name = "아카이브 > 업로드", description = "아카이브 서버로 파일을 전송합니다.")
@Slf4j
@RequestMapping(value = "/archive")
public class UploadController {
  private final UploadFileToServer uploadFileToServer;
  private final FileChecker fileChecker;

  @PostMapping(value = "/upload")
  @Operation(summary = "업로드", description = "컨버팅을 거치지 않고 영상을 업로드 합니다.")
  public void MediaUpload(MultipartFile files, HttpServletResponse response) throws IOException {
    String redirect_uri = "redirect:/";
    Resource mediaFiles = files.getResource();

    try {
      fileChecker.fileNull(mediaFiles);
      uploadFileToServer.upload(mediaFiles);
      response.sendRedirect(redirect_uri);
    } catch (NullPointerException e) {
      log.error(e.getMessage());
    }
  }
}
