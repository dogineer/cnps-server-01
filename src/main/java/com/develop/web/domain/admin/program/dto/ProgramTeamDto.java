package com.develop.web.domain.admin.program.dto;

import lombok.Data;

@Data
public class ProgramTeamDto {
    private Integer programId;
    private String programName;
    private Integer programParentId;
}
