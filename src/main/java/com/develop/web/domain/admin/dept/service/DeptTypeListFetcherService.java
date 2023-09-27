package com.develop.web.domain.admin.dept.service;

import com.develop.web.domain.admin.dept.dto.DeptDetailDto;
import com.develop.web.domain.admin.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptTypeListFetcherService {
    private final DeptMapper deptMapper;

    public List<DeptDetailDto> findDeptType(Integer deptParentId){
        return deptMapper.selectDeptType(deptParentId);
    }
}
