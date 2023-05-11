package com.develop.web.domain.folder.mapper;

import com.develop.web.domain.folder.dto.FolderClipDto;
import com.develop.web.domain.folder.dto.FolderDto;
import com.develop.web.domain.media.groupclip.dto.GFolderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolderMapper {
    List<FolderDto> selectFolderRootList();
    List<FolderDto> selectFolderChildrenList(Integer num);
    List<FolderClipDto> selectFolderClipData(Integer folderId);
    void insertNewFolder(FolderDto folderDto);

    List<GFolderDto> selectTeamFolderList(Integer teamId);


}
