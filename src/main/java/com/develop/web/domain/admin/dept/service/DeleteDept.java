package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.mapper.DeptMapper;
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
