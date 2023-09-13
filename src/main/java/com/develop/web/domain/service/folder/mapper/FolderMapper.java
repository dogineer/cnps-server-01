package com.develop.web.domain.service.folder.mapper;

import com.develop.web.domain.service.folder.dto.FolderClipDto;
import com.develop.web.domain.service.folder.dto.FolderDto;
import com.develop.web.domain.service.folder.dto.GroupFolderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolderMapper {
    List<FolderDto> selectFolderRootList();
    List<FolderDto> selectFolderChildrenList(Integer num);
    List<FolderClipDto> selectFolderClipData(Integer folderId);

    void insertNewFolder(FolderDto folderDto);

    List<GroupFolderDto> selectTeamFolderList(Integer teamId);
    List<GroupFolderDto> selectTeamFolderListForOnlyAdmin();


}
