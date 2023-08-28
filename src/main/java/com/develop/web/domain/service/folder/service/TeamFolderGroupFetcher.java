package com.develop.web.domain.service.folder.service;

import com.develop.web.domain.service.folder.mapper.FolderMapper;
import com.develop.web.domain.service.folder.dto.GFolderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamFolderGroupFetcher {
        private final FolderMapper folderMapper;

    public List<GFolderDto> getTeamFolder(Integer teamId, Integer rankId) {
        System.out.println(rankId);
        if (rankId == 12){
            return folderMapper.selectTeamFolderListForOnlyAdmin();
        }
        return folderMapper.selectTeamFolderList(teamId);
    }
}
