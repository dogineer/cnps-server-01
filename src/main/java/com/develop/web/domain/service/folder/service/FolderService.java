package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.folder.dto.FolderDetailsDto;
import com.develop.web.domain.service.folder.dto.FolderDto;
import com.develop.web.domain.service.folder.dto.ProgramFolderDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderMapper folderMapper;

    public void addFolder(FolderDto folderDto) {
        folderMapper.insertNewFolder(folderDto);
    }

    public void removeFolder(Integer folderId) {
        folderMapper.deleteFolder(folderId);
    }

    public List<ClipDto> findFolderClipData(Integer folderId){
        return folderMapper.selectFolderClipData(folderId);
    }

    public List<ProgramFolderDto> findFolder() {
        return folderMapper.selectFolderList();
    }

    public List<FolderDetailsDto> findSubfolder(Integer num){
        return folderMapper.selectSubfolderList(num);
    }
}
