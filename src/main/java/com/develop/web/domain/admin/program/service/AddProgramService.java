package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramDto;
import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProgramService {
    private final AdminProgramMapper adminProgramMapper;

    public void insertProgram(ProgramDto programDto) {
        adminProgramMapper.addProgram(programDto);
    }
}
