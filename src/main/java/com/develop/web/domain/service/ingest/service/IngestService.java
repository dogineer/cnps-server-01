package com.develop.web.domain.service.ingest.service;

import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.service.ingest.dto.IngestListDto;
import com.develop.web.domain.service.ingest.mapper.UploadMapper;
import com.develop.web.domain.service.ingest.dto.IngestRequestData;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngestService {

    private final UploadMapper uploadMapper;

    /** @description 인제스트 요청 글 작성 */
    public void addIngestRequest(IngestRequestData requestData) {
        uploadMapper.insertIngestRequest(requestData);
    }

    /** @description 인제스트 리스트 가져오기 */
    public List<IngestListDto> findIngestRequestList(CriteriaDto cri) {
        return uploadMapper.selectGetIngestList(cri);
    }

    /** @description 임시 리소스 만들기 */
    public Resource createTempFile(MultipartFile multipartFile, String path) throws IOException {
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
