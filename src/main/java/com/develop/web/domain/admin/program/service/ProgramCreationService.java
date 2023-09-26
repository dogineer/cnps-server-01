package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import com.develop.web.domain.users.program.dto.ProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramCreationService {
    private final AdminProgramMapper adminProgramMapper;

    public void addProgram(ProgramDto programDto) {
        adminProgramMapper.insertProgram(programDto);
    }
}
