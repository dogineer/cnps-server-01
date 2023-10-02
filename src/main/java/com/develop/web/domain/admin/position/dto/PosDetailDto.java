package com.develop.web.domain.admin.position.dto;

import lombok.Data;

@Data
public class PosDetailDto {
    private Integer posId;
    private String posName;
    private Integer posParentId;
}
