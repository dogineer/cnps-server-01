package com.develop.web.domain.personnel.dept.mapper;

import com.develop.web.domain.personnel.dept.dto.DeptDto;
import com.develop.web.domain.personnel.dept.dto.NewDeptDto;
import com.develop.web.domain.personnel.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    void insertDept(NewDeptDto deptDto);
    void deleteDept(Integer deptId);

    List<Member> selectDeptMembers(Integer deptId);

    List<DeptDto> selectDeptList();
    List<DeptDto> selectTopDept();

    String selectDetailDept(String account);
}
