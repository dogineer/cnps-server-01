package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.dto.GroupFolderDto;
import com.develop.web.domain.service.folder.mapper.FolderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamFolderGroupFetcher {
        private final FolderMapper folderMapper;

    public List<GroupFolderDto> getTeamFolder(Integer teamId, Integer rankId) {
        if (rankId == 12){
            return folderMapper.selectTeamFolderListForOnlyAdmin();
        }
        return folderMapper.selectTeamFolderList(teamId);
    }
}
