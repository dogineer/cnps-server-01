package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTeamService {
    private final AdminTeamMapper adminTeamMapper;

    public void deleteProgram(Integer programId) {
        adminTeamMapper.deleteProgram(programId);
    }
}
