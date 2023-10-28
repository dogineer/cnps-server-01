package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.dto.ProgramFolderDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramFolderService {
        private final FolderMapper folderMapper;

    public List<ProgramFolderDto> findProgramFolderRoot(Integer programId, Boolean isAdmin) {
        if (isAdmin){
            return folderMapper.selectProgramFolderListForOnlyAdmin();
        }
        return folderMapper.selectProgramFolderRootList(programId);
    }

    public List<ProgramFolderDto> findProgramFolder(Integer programId, Boolean isAdmin) {
        if (isAdmin){
            return folderMapper.selectFolderList();
        }
        return folderMapper.selectProgramFolderList(programId);
    }
}
