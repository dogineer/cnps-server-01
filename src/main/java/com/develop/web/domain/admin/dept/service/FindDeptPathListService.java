package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.DeptPathDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindDeptPathListService {
    public final DeptMapper deptMapper;

    public List<DeptPathDto> getDeptPathList(Integer deptId){
        return deptMapper.selectFindDeptPath(deptId);
    }
}
