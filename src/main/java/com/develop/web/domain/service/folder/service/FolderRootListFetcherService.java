package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.dto.ProgramFolderDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderRootListFetcherService {
    private final FolderMapper folderMapper;

    public List<ProgramFolderDto> findFolder() {
        return folderMapper.selectFolderList();
    }
}
