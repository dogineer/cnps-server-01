package com.develop.web.domain.folder.controller;

import com.develop.web.domain.folder.dto.FolderDto;
import com.develop.web.domain.folder.service.ChildrenFolderFetcher;
import com.develop.web.domain.folder.service.CreateNewFolder;
import com.develop.web.domain.folder.service.RootFolderListFetcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "프로그램 관리", description = "Swagger 테스트용 API")
@RequestMapping(value = "/folder")
public class FolderController {
    private final CreateNewFolder createNewFolder;
    private final RootFolderListFetcher rootFolderListFetcher;
    private final ChildrenFolderFetcher childrenFolderFetcher;

    @PostMapping("/create")
    @Operation(summary = "상위 폴더 조회", description = "상위 폴더에 해당하는 폴더만 조회합니다.")
    public String createNewFolder(String name){
        createNewFolder.addFolder(name);
        return "redirect:/home";
    }

    @GetMapping("/show/{num}")
    @Operation(summary = "하위 폴더 조회", description = "상위 폴더 안에 있는 하위 폴더를 조회합니다.")
    public List<FolderDto> ShowFolderList(@PathVariable Integer num){

        return childrenFolderFetcher.getChildrenFolder(num);
    }
}
