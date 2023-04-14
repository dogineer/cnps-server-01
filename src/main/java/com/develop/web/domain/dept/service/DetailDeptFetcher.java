package com.develop.web.domain.dept.service;

import com.develop.web.domain.dept.mapper.DeptMapper;
import org.springframework.stereotype.Service;

@Service
public class DetailDeptFetcher {
    private final DeptMapper deptMapper;

    public DetailDeptFetcher(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

     public String getDetailDept(String account){
        return deptMapper.selectDetailDept(account);
    }
}
