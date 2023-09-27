package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import com.develop.web.domain.users.program.dto.ProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramFetcherService {
    private final AdminProgramMapper adminProgramMapper;

    public List<ProgramDto> findProgram(Integer programId){
        return adminProgramMapper.selectProgramList(programId);
    }
}
