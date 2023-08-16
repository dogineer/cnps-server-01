package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.mapper.FolderMapper;
import com.develop.web.domain.service.folder.dto.GFolderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamFolderGroupFetcher {
        private final FolderMapper folderMapper;

    public List<GFolderDto> getTeamFolder(Integer teamId) {
        return folderMapper.selectTeamFolderList(teamId);
    }
}
