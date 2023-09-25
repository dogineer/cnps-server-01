package com.develop.web.domain.users.program.mapper;

import com.develop.web.domain.users.program.dto.ProgramUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProgramMapper {
    List<ProgramUserDto> selectBelongProgramList(Integer programId, Integer rankId);
    List<ProgramUserDto> selectProgramList();
    List<ProgramUserDto> selectProgramTypeList();
}
