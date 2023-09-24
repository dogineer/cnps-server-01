package com.develop.web.domain.admin.program.mapper;

import com.develop.web.domain.admin.program.dto.ProgramTeamDto;
import com.develop.web.domain.admin.program.dto.ProgramDto;
import com.develop.web.domain.admin.program.dto.ProgramPathDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminTeamMapper {

    void addProgram(ProgramDto programDto);

    void deleteProgram(Integer programId);

    List<ProgramTeamDto> selectProgramTeamAllList();

    List<ProgramDto> selectProgramTypeList();

    List<ProgramDto> selectProgramList(Integer programId);

    List<ProgramPathDto> selectPathList(Integer programId);
}
