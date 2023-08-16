package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindDeptChartList {
    public final DeptMapper deptMapper;

    public List<String> getDeptChartList(Integer deptId){
        return deptMapper.selectFindDeptChart(deptId);
    }
}
