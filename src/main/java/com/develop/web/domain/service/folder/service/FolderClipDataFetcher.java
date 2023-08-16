package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.dto.FolderClipDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderClipDataFetcher{
    private final FolderMapper folderMapper;

    public List<FolderClipDto> getFolderClipData(Integer folderId){
        return folderMapper.selectFolderClipData(folderId);
    }

}
