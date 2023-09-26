package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderClipDataFetcherService {
    private final FolderMapper folderMapper;

    public List<ClipDto> findFolderClipData(Integer folderId){
        return folderMapper.selectFolderClipData(folderId);
    }

}
