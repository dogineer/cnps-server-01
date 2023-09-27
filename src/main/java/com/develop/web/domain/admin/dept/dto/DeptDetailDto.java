package com.develop.web.domain.admin.dept.dto;

import lombok.Data;

@Data
public class DeptDetailDto {
    private Integer deptId;
    private String deptName;
    private Integer deptParentId;
}
