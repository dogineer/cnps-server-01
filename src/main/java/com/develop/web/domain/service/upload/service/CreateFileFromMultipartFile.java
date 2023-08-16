package com.develop.web.domain.service.upload.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class CreateFileFromMultipartFile {
    public Resource run(MultipartFile multipartFile, String path) throws IOException {
        String randomString = RandomStringUtils.random(10, "0123456789abcdef");
        String fileName = path + randomString + File.separator + multipartFile.getOriginalFilename();
        File convertFile = new File(fileName);

        Path filePath = convertFile.toPath();
        Files.createDirectories(filePath.getParent());

        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            StreamUtils.copy(multipartFile.getInputStream(), fos);
        }

        log.trace("MultipartFile에서 임시 파일 생성 완료 : " + convertFile.getAbsolutePath());

        return new FileSystemResource(convertFile);
    }
}