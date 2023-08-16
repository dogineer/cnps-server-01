package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.NewDeptDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewDept {
    private final DeptMapper deptMapper;

    public void setNewDept(NewDeptDto deptDto){
        deptMapper.insertDept(deptDto);
    }
}
