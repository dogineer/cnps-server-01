package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramDto;
import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchProgramTypeList {
    private final AdminProgramMapper adminProgramMapper;

    public List<ProgramDto> getTypeList() {
        return adminProgramMapper.selectProgramTypeList();
    }
}
