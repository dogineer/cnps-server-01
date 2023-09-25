package com.develop.web.domain.service.ingest.controller;

import com.develop.web.domain.service.ingest.dto.IngestListDto;
import com.develop.web.domain.service.ingest.service.CreateIngestPost;
import com.develop.web.domain.service.ingest.service.IngestListFetcher;
import com.develop.web.domain.service.ingest.dto.IngestRequestData;
import com.develop.web.domain.service.ingest.service.CreateFileFromMultipartFileService;
import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.service.ingest.service.ServerFileUploader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "미디어 > 인제스트", description = "등록 -> 컨버팅 -> 완료의 한 집합")
@Slf4j
@RequestMapping(value = "/ingest")
public class IngestController {
    private final IngestListFetcher ingestListFetcher;
    private final ServerFileUploader serverFileUploader;
    private final CreateFileFromMultipartFileService createFileFromMultipartFileService;
    private final CreateIngestPost createIngestPost;

    @Value("${app.temp.dir:${user.home}/media-buddies/temp/}")
    private String TempDir;

    @Transactional
    @PostMapping(value = "/add")
    @Operation(summary = "인제스트 등록", description = "서버2로 데이터 보냅니다.")
    public void ingestRequset(IngestRequestData ingestRequestData, HttpSession session) throws IOException {
        Integer memberId = session.getAttribute("empId").hashCode();
        Integer programId = session.getAttribute("programId").hashCode();

        ingestRequestData.setMemberId(memberId);
        ingestRequestData.setProgramId(programId);
        createIngestPost.addIngestRequest(ingestRequestData);

        Resource mediaFiles = createFileFromMultipartFileService.run(ingestRequestData.getFiles(), TempDir);
        serverFileUploader.uploadFileAndIngestId(mediaFiles, ingestRequestData);
    }

    @GetMapping(value = "/list")
    @Operation(summary = "인제스트 목록", description = "인제스트 목록 현황을 가져옵니다.")
    public List<IngestListDto> ingestList() {
        CriteriaDto criteriaDto = new CriteriaDto();
        return ingestListFetcher.getIngestRequestList(criteriaDto);
    }
}
