package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramDto;
import com.develop.web.domain.admin.program.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTeamService {
    private final AdminTeamMapper adminTeamMapper;

    public void insertProgram(ProgramDto programDto) {
        adminTeamMapper.addProgram(programDto);
    }
}
