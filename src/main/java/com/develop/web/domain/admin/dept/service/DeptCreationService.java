package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeptCreationService {
    private final DeptMapper deptMapper;

    public void addDept(DeptDto deptDto) {
        deptMapper.insertDept(deptDto);
    }
}
