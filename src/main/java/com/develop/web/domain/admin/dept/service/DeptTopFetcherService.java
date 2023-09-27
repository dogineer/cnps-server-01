package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.DeptDetailDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptTopFetcherService {
    private final DeptMapper deptMapper;

    public DeptTopFetcherService(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    public List<DeptDetailDto> findTopDept() {
        return deptMapper.selectTopDept();
    }
}
