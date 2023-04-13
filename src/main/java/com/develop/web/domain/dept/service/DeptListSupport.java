package com.develop.web.domain.dept.service;

import com.develop.web.domain.dept.dto.DeptDto;
import com.develop.web.domain.dept.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptListSupport {
    private final DeptMapper deptMapper;

    public DeptListSupport(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    public List<DeptDto> getAll(){
        return deptMapper.queryListDept();
    }
}
