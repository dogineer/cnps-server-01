package com.develop.web.domain.team.service;

import com.develop.web.domain.team.mapper.TeamMapper;
import com.develop.web.domain.team.dto.TeamDto;
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
}
