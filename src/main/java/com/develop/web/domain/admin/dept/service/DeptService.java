package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.DeptDetailDto;
import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.dto.DeptPathDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptService {
    private final DeptMapper deptMapper;

    public void addDept(DeptDto deptDto) {
        deptMapper.insertDept(deptDto);
    }

    public void deleteDept(Integer deptId) {
        deptMapper.deleteDept(deptId);
    }

    public List<DeptDetailDto> findDeptList() {
        return deptMapper.selectDept();
    }

    public List<DeptPathDto> findDeptPathList(Integer deptId){
        return deptMapper.selectDeptPath(deptId);
    }

    public List<DeptDetailDto> findTopDept() {
        return deptMapper.selectTopDept();
    }

    public List<DeptDetailDto> findDeptType(Integer deptParentId){
        return deptMapper.selectDeptType(deptParentId);
    }
}
