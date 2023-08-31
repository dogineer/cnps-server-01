package com.develop.web.domain.service.ingest.controller;

import com.develop.web.domain.service.ingest.dto.ResultRequestData;
import com.develop.web.domain.service.ingest.service.CreateClipPost;
import com.develop.web.domain.service.ingest.service.CreateIngestPost;
import com.develop.web.domain.service.ingest.service.FileCheckerService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "영상 > 인제스트", description = "")
@Slf4j
@RequestMapping(value = "/ingest")
public class IngestController {
    private final IngestListFetcher ingestListFetcher;
    private final ServerFileUploader serverFileUploader;
    private final CreateFileFromMultipartFileService createFileFromMultipartFileService;
    private final CreateClipPost createClipPost;
    private final CreateIngestPost createIngestPost;
    private final FileCheckerService fileCheckerService;

    @Value("${app.temp.dir:${user.home}/media-buddies/temp/}")
    private String TempDir;

    @Transactional
    @PostMapping(value = "/add")
    @Operation(summary = "인제스트", description = "업로드 -> temp 임시 파일 생성 -> 미디어센터 서버로 이동 -> 영상 아카이브 저장 및 변환 -> 컨버팅 저장 -> 완료 )")
    public void ingestRequset(IngestRequestData ingestRequestData, HttpSession session) throws IOException {
        Integer memberId = session.getAttribute("empId").hashCode();
        Integer teamId = session.getAttribute("teamId").hashCode();

        ingestRequestData.setMemberId(memberId);
        ingestRequestData.setTeamId(teamId);

        fileCheckerService.isFileNull(ingestRequestData);
        createIngestPost.addIngestRequest(ingestRequestData);

        MultipartFile file = ingestRequestData.getFiles();
        Resource mediaFiles = createFileFromMultipartFileService.run(file, TempDir);
        Integer ingestId = ingestRequestData.getId();

        ResultRequestData requestData = new ResultRequestData();

        serverFileUploader
            .uploadFileAndIngestId(mediaFiles, ingestId)
            .subscribe(metadata -> {
                requestData.ingest_id = ingestRequestData.getId();
                requestData.team_id = ingestRequestData.getTeamId();
                requestData.folder_id = ingestRequestData.getFolder();
                requestData.a_metadata_id = metadata.id;
                createClipPost.addClipPost(requestData);
                log.info("[!] 인제스트를 마쳤습니다. \n" + requestData.toString());
            });
    }

    @GetMapping(value = "/list")
    @Operation(summary = "인제스트 목록", description = "인제스트 목록 현황을 가져옵니다.")
    public List<IngestRequestData> ingestList() {
        CriteriaDto criteriaDto = new CriteriaDto();
        return ingestListFetcher.getIngestRequestList(criteriaDto);
    }
}
