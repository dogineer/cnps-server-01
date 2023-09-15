package com.develop.web.domain.admin.rank.dto;

import lombok.Data;

@Data
public class NewRankDto {
    String rankName;
    Integer rankParentId;
}
