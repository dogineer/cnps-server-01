package com.develop.web.domain.admin.program.mapper;

import com.develop.web.domain.admin.program.dto.ProgramRecursionDto;
import com.develop.web.domain.admin.program.dto.ProgramDto;
import com.develop.web.domain.admin.program.dto.ProgramPathDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminProgramMapper {

    void addProgram(ProgramDto programDto);
    void deleteProgram(Integer programId);
    List<ProgramRecursionDto> selectProgramRecursionAllList();
    List<ProgramDto> selectProgramList(Integer programId);
    List<ProgramDto> selectProgramTypeList();
    List<ProgramPathDto> selectPathList(Integer programId);
}
