package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramPathDto;
import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchProgramPathList {
    private final AdminProgramMapper adminProgramMapper;

    public List<ProgramPathDto> findProgramPathList(Integer programId){
        return adminProgramMapper.selectProgramPathList(programId);
    }
}
