package com.develop.web.domain.admin.team.service;

import com.develop.web.domain.admin.team.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTeamService {
    private final AdminTeamMapper adminTeamMapper;

    public void deleteTeam(Integer teamId) {
        adminTeamMapper.deleteTeam(teamId);
    }
}
