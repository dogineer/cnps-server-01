package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.dto.GroupFolderDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramFolderGroupFetcher {
        private final FolderMapper folderMapper;

    public List<GroupFolderDto> getProgramFolder(Integer programId, Integer rankId) {
        if (rankId == 12){
            return folderMapper.selectProgramFolderListForOnlyAdmin();
        }
        return folderMapper.selectProgramFolderList(programId);
    }
}
