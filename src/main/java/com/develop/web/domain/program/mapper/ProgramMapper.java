package com.develop.web.domain.program.mapper;

import com.develop.web.domain.program.programDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProgramMapper {
    void add(String name);
    List<programDto> list();
}
