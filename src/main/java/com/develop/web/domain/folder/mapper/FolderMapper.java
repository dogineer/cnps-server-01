package com.develop.web.domain.folder.mapper;

import com.develop.web.domain.folder.dto.FolderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolderMapper {
    List<FolderDto> selectFolderList();
    void insertNewFolder(String name);
}
