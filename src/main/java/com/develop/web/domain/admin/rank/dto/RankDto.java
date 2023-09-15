package com.develop.web.domain.admin.rank.dto;

import lombok.Data;

@Data
public class RankDto {
    private Integer rankId;
    private String rankName;
    private Integer rankParentId;
}
