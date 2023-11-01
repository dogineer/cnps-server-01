package com.develop.web.domain.service.folder.mapper;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.folder.dto.FolderDetailsDto;
import com.develop.web.domain.service.folder.dto.FolderDto;
import com.develop.web.domain.service.folder.dto.ProgramFolderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolderMapper {
    List<ProgramFolderDto> selectFolderList();
    List<FolderDetailsDto> selectSubfolderList(Integer num);

    List<ProgramFolderDto> selectProgramFolderList(Integer programId);
    List<ProgramFolderDto> selectProgramFolderRootList(Integer programId);
    List<ProgramFolderDto> selectProgramFolderListForOnlyAdmin();

    List<ClipDto> selectFolderClipData(Integer folderId);

    void insertNewFolder(FolderDto folderDto);
    void deleteFolder(Integer folderId);

    boolean selectSubfolderCheck(Integer folderId);
}
