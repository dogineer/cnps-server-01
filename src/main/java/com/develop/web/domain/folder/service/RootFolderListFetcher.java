package com.develop.web.domain.folder.service;

import com.develop.web.domain.folder.dto.FolderDto;
import com.develop.web.domain.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderRootListFetcher {
    private final FolderMapper folderMapper;

    public List<FolderDto> getFolder() {
        return folderMapper.selectFolderRootList();
    }
}
