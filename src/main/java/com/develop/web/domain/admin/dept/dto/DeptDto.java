package com.develop.web.domain.admin.dept.dto;

import lombok.Data;

@Data
public class DeptDto {
    private Integer deptId;
    private String deptName;
    private Integer deptParentId;
}
