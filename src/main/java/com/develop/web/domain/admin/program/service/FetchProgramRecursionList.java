package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramRecursionDto;
import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchProgramRecursionList {
    private final AdminProgramMapper adminProgramMapper;

    public List<ProgramRecursionDto> getList() {
        return adminProgramMapper.selectProgramRecursionAllList();
    }
}
