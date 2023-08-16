package com.develop.web.domain.admin.team.service;

import com.develop.web.domain.admin.team.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTeamService {
    private final AdminTeamMapper adminTeamMapper;

    public void insertTeam(String teamName, Integer teamType) {
        adminTeamMapper.addTeam(teamName, teamType);
    }
}
