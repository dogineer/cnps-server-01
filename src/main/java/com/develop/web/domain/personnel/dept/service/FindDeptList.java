package com.develop.web.domain.personnel.dept.service;

import com.develop.web.domain.personnel.dept.dto.DeptDto;
import com.develop.web.domain.personnel.dept.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindDeptList {
    private final DeptMapper deptMapper;

    public FindDeptList(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    public List<DeptDto> getDeptList(){
        return deptMapper.selectDeptList();
    }
}
