package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramPathDto;
import com.develop.web.domain.admin.program.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchTeamPathList {
    private final AdminTeamMapper adminTeamMapper;

    public List<ProgramPathDto> getPathList(Integer programId){
        return adminTeamMapper.selectPathList(programId);
    }
}
