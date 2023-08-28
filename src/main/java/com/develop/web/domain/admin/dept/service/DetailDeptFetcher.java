package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailDeptFetcher {
    private final DeptMapper deptMapper;

    public String getDetailDept(String account) {
        return deptMapper.selectDetailDept(account);
    }
}
