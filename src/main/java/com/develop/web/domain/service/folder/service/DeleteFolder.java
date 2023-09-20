package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFolder {
    private final FolderMapper folderMapper;

    public void deleteFolder(Integer folderDto) {
        folderMapper.deleteFolde(folderDto);
    }
}
