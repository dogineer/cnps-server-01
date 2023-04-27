package com.develop.web.domain.personnel.dept.service;

import com.develop.web.domain.personnel.dept.dto.NewDeptDto;
import com.develop.web.domain.personnel.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteDept {
    private final DeptMapper deptMapper;

    public void setDeleteDept(Integer deptId){
        deptMapper.deleteDept(deptId);
    }
}
