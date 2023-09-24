package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramDto;
import com.develop.web.domain.admin.program.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindProgramService {
    private final AdminTeamMapper adminTeamMapper;

    public List<ProgramDto> getProgram(Integer programId){
        return adminTeamMapper.selectProgramList(programId);
    }
}
