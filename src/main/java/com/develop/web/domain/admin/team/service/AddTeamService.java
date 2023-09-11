package com.develop.web.domain.admin.team.service;

import com.develop.web.domain.admin.team.dto.RequsetTeamForAdminDto;
import com.develop.web.domain.admin.team.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTeamService {
    private final AdminTeamMapper adminTeamMapper;

    public void insertTeam(RequsetTeamForAdminDto requsetTeamForAdminDto) {
        adminTeamMapper.addTeam(requsetTeamForAdminDto);
    }
}
