package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeptDeletionService {
    private final DeptMapper deptMapper;

    public void deleteDept(Integer deptId) {
        deptMapper.deleteDept(deptId);
    }
}
