package com.develop.web.domain.service.folder.mapper;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.folder.dto.FolderDto;
import com.develop.web.domain.service.folder.dto.GroupFolderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolderMapper {
    List<FolderDto> selectFolderRootList();
    List<FolderDto> selectFolderChildrenList(Integer num);
    List<ClipDto> selectFolderClipData(Integer folderId);

    void insertNewFolder(FolderDto folderDto);
    void deleteFolder(Integer folderId);

    List<GroupFolderDto> selectProgramFolderList(Integer programId);
    List<GroupFolderDto> selectProgramFolderListForOnlyAdmin();


}
