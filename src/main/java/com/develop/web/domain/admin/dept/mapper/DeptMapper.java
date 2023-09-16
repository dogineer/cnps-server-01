package com.develop.web.domain.admin.dept.mapper;

import com.develop.web.domain.admin.dept.dto.DeptDto;
import com.develop.web.domain.admin.dept.dto.PdeptDto;
import com.develop.web.domain.users.user.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    void insertDept(PdeptDto pdeptDto);

    void deleteDept(Integer deptId);

    List<DeptDto> selectDeptList();

    List<DeptDto> selectHighDept();

    List<DeptDto> selectDeptMidList(Integer deptParentId);

    List<Member> selectDeptMembers(Integer deptId);

    List<String> selectFindDeptChart(Integer deptId);
}
