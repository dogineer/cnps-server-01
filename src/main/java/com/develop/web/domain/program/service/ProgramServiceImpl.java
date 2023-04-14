package com.develop.web.domain.program.service;

import com.develop.web.domain.program.mapper.ProgramMapper;
import com.develop.web.domain.program.programDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramMapper programMapper;

    public ProgramServiceImpl(ProgramMapper programMapper) {
        this.programMapper = programMapper;
    }

    @Override
    public void add(String name) {
        programMapper.add(name);
    }

    @Override
    public List<programDto> list() {
        return programMapper.list();
    }
}
