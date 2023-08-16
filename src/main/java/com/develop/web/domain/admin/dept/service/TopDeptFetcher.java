package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopDeptFetcher {
    private final DeptMapper deptMapper;

    public TopDeptFetcher(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

     public List<DeptDto> getTopDept(){
        return deptMapper.selectTopDept();
    }
}
