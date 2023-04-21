package com.develop.web.domain.folder.controller;

import com.develop.web.domain.folder.dto.FolderDto;
import com.develop.web.domain.folder.service.CreateNewFolder;
import com.develop.web.domain.folder.service.FolderListFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/folder")
public class FolderController {
    private final CreateNewFolder createNewFolder;
    private final FolderListFetcher folderListFetcher;

    @PostMapping("/create")
    public String createNewFolder(String name){
        createNewFolder.addFolder(name);
        return "pages/home";
    }

    @GetMapping("/show")
    public void ShowFolderList(){
        List<FolderDto> folderDatas = folderListFetcher.getFolder();
    }
}
