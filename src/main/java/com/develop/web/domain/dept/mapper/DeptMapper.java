package com.develop.web.domain.dept.mapper;

import com.develop.web.domain.dept.dto.DeptDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    /** @description 부서 데이터 불러오기 */
    List<DeptDto> selectDeptList();

    String selectDetailDept(String account);
}
