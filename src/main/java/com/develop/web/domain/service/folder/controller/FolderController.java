package com.develop.web.domain.service.folder.controller;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.folder.dto.FolderDetailsDto;
import com.develop.web.domain.service.folder.dto.FolderDto;
import com.develop.web.domain.service.folder.dto.ProgramFolderDto;
import com.develop.web.domain.service.folder.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "미디어 > 폴더", description = "프로그램 폴더 관리")
@RequestMapping(value = "/s1/api/folder")
public class FolderController {
    private final FolderService folderService;
    private final ProgramFolderService programFolderService;

    @PostMapping("/create")
    @Operation(summary = "폴더 생성", description = "폴더를 생성합니다.")
    public void folderAdd(@RequestBody FolderDto folderDto) {
        folderService.addFolder(folderDto);
    }

    @DeleteMapping("/delete/{folderId}")
    @Operation(summary = "폴더 삭제", description = "폴더를 삭제합니다. 폴더 id가 등록된 데이터는 삭제되지 않습니다.")
    public void folderRemove(@PathVariable Integer folderId) {
        folderService.removeFolder(folderId);
    }

    @GetMapping("/show/all")
    @Operation(summary = "폴더 전체 조회", description = "폴더 전체를 조회합니다.")
    public List<ProgramFolderDto> folderList() {
        return folderService.findFolder();
    }

    @GetMapping("/show/{num}")
    @Operation(summary = "하위 폴더 조회", description = "상위 폴더 안에 있는 하위 폴더를 조회합니다. 상위 폴더의 코드를 입력해주세요.")
    public List<FolderDetailsDto> folderDetails(@PathVariable Integer num) {
        return folderService.findSubfolder(num);
    }

    @GetMapping("/show/program/{programId}/role/{isAdmin}")
    @Operation(summary = "프로그램 폴더 조회", description = "프로그램에 속한 폴더들만 조회합니다.")
    public List<ProgramFolderDto> programDetails(@PathVariable Integer programId, @PathVariable Boolean isAdmin) {
        return programFolderService.findProgramFolderRoot(programId, isAdmin);
    }

    @GetMapping("/select/{folderId}")
    @Operation(summary = "폴더 데이터 조회", description = "그룹 폴더의 클립 데이터를 보여줍니다.")
    public List<ClipDto> folderClipDetails(@PathVariable Integer folderId) {
        return folderService.findFolderClipData(folderId);
    }
}
