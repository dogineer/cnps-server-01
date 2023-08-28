package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetcherDeptHigh {
    private final DeptMapper deptMapper;

    public FetcherDeptHigh(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    public List<DeptDto> getHighDept() {
        return deptMapper.selectHighDept();
    }
}
