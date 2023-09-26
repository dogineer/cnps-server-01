package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramDeleteionService {
    private final AdminProgramMapper adminProgramMapper;

    public void removeProgram(Integer programId) {
        adminProgramMapper.deleteProgram(programId);
    }
}
