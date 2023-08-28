package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.PdeptDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddDeptService {
    private final DeptMapper deptMapper;

    public void insertDept(PdeptDto pdeptDto) {
        deptMapper.insertDept(pdeptDto);
    }
}
