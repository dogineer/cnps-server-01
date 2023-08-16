package com.develop.web.domain.users.team.service;

import com.develop.web.domain.users.team.mapper.TeamMapper;
import com.develop.web.domain.users.team.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamListFetcher {

    private final TeamMapper teamMapper;

    public List<TeamDto> getTeam() {
        return teamMapper.selectTeamList();
    }
    public List<TeamDto> getTeamType() {
        return teamMapper.selectTeamTypeList();
    }
}
