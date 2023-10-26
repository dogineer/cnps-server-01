package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramPathDto;
import com.develop.web.domain.admin.program.dto.ProgramRecursionDto;
import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import com.develop.web.domain.member.program.dto.ProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final AdminProgramMapper adminProgramMapper;

    public void addProgram(ProgramDto programDto) {
        adminProgramMapper.insertProgram(programDto);
    }

    public void removeProgram(Integer programId) {
        adminProgramMapper.deleteProgram(programId);
    }

    public List<ProgramRecursionDto> findProgramList() {
        return adminProgramMapper.selectProgramRecursionAllList();
    }

    public List<ProgramDto> findProgramTypeList() {
        return adminProgramMapper.selectProgramTypeList();
    }

    public List<ProgramDto> findProgram(Integer programId){
        return adminProgramMapper.selectProgramList(programId);
    }

    public List<ProgramPathDto> findProgramPathList(Integer programId){
        return adminProgramMapper.selectProgramPathList(programId);
    }
}
