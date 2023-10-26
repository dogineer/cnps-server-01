package com.develop.web.domain.member.program.service;

import com.develop.web.domain.member.program.mapper.ProgramMapper;
import com.develop.web.domain.member.program.dto.ProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramListFetcher {

    private final ProgramMapper programMapper;

    public List<ProgramDto> findCurrentProgram(Integer programId, Boolean isAdmin) {
        if (isAdmin) {
            return programMapper.selectProgramList();
        } else
            return programMapper.selectCurrentProgram(programId);
    }

    public List<ProgramDto> findProgram() {
        return programMapper.selectProgramList();
    }

    public List<ProgramDto> findProgramType() {
        return programMapper.selectProgramTypeList();
    }
}
