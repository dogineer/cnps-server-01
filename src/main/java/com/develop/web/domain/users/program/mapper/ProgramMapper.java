package com.develop.web.domain.users.program.mapper;

import com.develop.web.domain.users.program.dto.ProgramDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProgramMapper {
    List<ProgramDto> selectCurrentProgram(Integer programId);
    List<ProgramDto> selectProgramList();
    List<ProgramDto> selectProgramTypeList();
}
