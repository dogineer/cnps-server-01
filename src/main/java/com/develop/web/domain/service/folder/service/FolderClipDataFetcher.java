package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderClipDataFetcher{
    private final FolderMapper folderMapper;

    public List<ClipDto> getFolderClipData(Integer folderId){
        return folderMapper.selectFolderClipData(folderId);
    }

}
