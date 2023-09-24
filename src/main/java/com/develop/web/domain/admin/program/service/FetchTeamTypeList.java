package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramDto;
import com.develop.web.domain.admin.program.mapper.AdminTeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchTeamTypeList {
    private final AdminTeamMapper adminTeamMapper;

    public List<ProgramDto> getTypeList() {
        return adminTeamMapper.selectProgramTypeList();
    }
}
