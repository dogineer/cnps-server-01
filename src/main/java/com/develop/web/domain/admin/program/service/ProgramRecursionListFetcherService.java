package com.develop.web.domain.admin.program.service;

import com.develop.web.domain.admin.program.dto.ProgramRecursionDto;
import com.develop.web.domain.admin.program.mapper.AdminProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramRecursionListFetcherService {
    private final AdminProgramMapper adminProgramMapper;

    public List<ProgramRecursionDto> findProgramList() {
        return adminProgramMapper.selectProgramRecursionAllList();
    }
}
