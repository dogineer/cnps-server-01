package com.develop.web.common.view.controller;

import com.develop.web.common.view.dto.CriteriaDto;
import com.develop.web.domain.admin.dept.dto.DeptDetailDto;
import com.develop.web.domain.admin.dept.service.DeptTypeListFetcherService;
import com.develop.web.domain.admin.dept.service.DeptListFetcherService;
import com.develop.web.domain.admin.position.dto.PosDetailDto;
import com.develop.web.domain.admin.position.service.PosListFetcherService;
import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.clip.service.ClipDataFetcherService;
import com.develop.web.domain.service.folder.dto.ProgramFolderDto;
import com.develop.web.domain.service.folder.service.FolderRootListFetcherService;
import com.develop.web.domain.service.ingest.dto.IngestListDto;
import com.develop.web.domain.service.ingest.service.IngestListFetcherService;
import com.develop.web.domain.users.program.dto.ProgramDto;
import com.develop.web.domain.users.program.service.ProgramListFetcher;
import com.develop.web.domain.users.user.dto.Userinfo;
import com.develop.web.domain.users.user.service.UserDetailFetcherService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/s1/api/common")
public class CommonApiController {
    private final FolderRootListFetcherService folderRootListFetcherService;
    private final ClipDataFetcherService clipDataFetcherService;
    private final UserDetailFetcherService userDetailFetcherService;
    private final ProgramListFetcher programListFetcher;
    private final DeptListFetcherService deptListFetcherService;
    private final IngestListFetcherService ingestListFetcherService;
    private final PosListFetcherService posListFetcherService;
    private final DeptTypeListFetcherService deptTypeListFetcherService;

    @GetMapping("/folder-list")
    @Operation(summary = "클립 페이지 > 폴더 데이터", description = "폴더 기초 데이터를 가져옵니다.")
    public List<ProgramFolderDto> pageClipFolderDataFetcher(){
        return folderRootListFetcherService.findFolder();
    }

    @GetMapping("/clip-list")
    @Operation(summary = "클립 페이지 > 미디어 데이터", description = "미디어 기초 데이터를 가져옵니다.")
    public List<ClipDto> pageClipMediaDataFetcher(CriteriaDto criteriaDto){
        return clipDataFetcherService.findClipList(criteriaDto);
    }

    @GetMapping("/ingest-list")
    @Operation(summary = "인제스트 페이지", description = "인제스트 데이터를 가져옵니다.")
    public List<IngestListDto> pageCommonIngestData(CriteriaDto criteriaDto) {
        return ingestListFetcherService.findIngestRequestList(criteriaDto);
    }

    @GetMapping("/user-info")
    @Operation(summary = "모든 페이지", description = "입력한 유저의 정보를 가져옵니다.")
    public Userinfo pageCommonMemberInfoData(String account) {
        return userDetailFetcherService.findMember(account);
    }

    @GetMapping("/program-list")
    @Operation(summary = "모든 페이지", description = "전체 팀 리스트를 가져옵니다.")
    public List<ProgramDto> pageCommonProgramInfoData() {
        return programListFetcher.findProgram();
    }

    @GetMapping("/dept-list")
    @Operation(summary = "모든 페이지", description = "전체 부서 리스트를 가져옵니다.")
    public List<DeptDetailDto> pageCommonDeptInfoData() {
        return deptListFetcherService.findDeptList();
    }

    @GetMapping("/pos-list")
    @Operation(summary = "모든 페이지", description = "랭크 리스트를 가져옵니다.")
    public List<PosDetailDto> pageCommonPosData() {
        return posListFetcherService.findPosList();
    }

    @GetMapping("/dept-type/{deptParentId}")
    @Operation(summary = "부서 유형 찾기", description = "본부의 id 값을 이용해 부서 유형을 찾습니다.")
    public List<DeptDetailDto> getTypeDeptList(@PathVariable Integer deptParentId){
        return deptTypeListFetcherService.findDeptType(deptParentId);
    }
}
