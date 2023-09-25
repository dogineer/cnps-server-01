package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProgramService {
    private final AdminProgramMapper adminProgramMapper;

    public void deleteProgram(Integer programId) {
        adminProgramMapper.deleteProgram(programId);
    }
}
