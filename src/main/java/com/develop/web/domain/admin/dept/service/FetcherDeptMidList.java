package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetcherDeptMidList {
    private final DeptMapper deptMapper;

    public List<DeptDto> getMidDept(Integer p_id){
        return deptMapper.selectDeptMidList(p_id);
    }
}
