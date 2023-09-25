package com.develop.web.domain.users.program.service;

import com.develop.web.domain.users.program.mapper.ProgramMapper;
import com.develop.web.domain.users.program.dto.ProgramUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramListFetcher {

    private final ProgramMapper programMapper;

    public List<ProgramUserDto> getBelongProgram(Integer programId, Integer rankId) {
        return programMapper.selectBelongProgramList(programId, rankId);
    }
    public List<ProgramUserDto> getProgram() {
        return programMapper.selectProgramList();
    }
    public List<ProgramUserDto> getProgramType() {
        return programMapper.selectProgramTypeList();
    }
}
