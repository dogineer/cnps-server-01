package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramTeamDto;
import com.develop.web.domain.admin.program.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchProgramTeamList {
    private final AdminTeamMapper adminTeamMapper;

    public List<ProgramTeamDto> getList() {
        return adminTeamMapper.selectProgramTeamAllList();
    }
}
