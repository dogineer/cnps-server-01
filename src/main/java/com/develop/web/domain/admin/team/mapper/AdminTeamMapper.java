package com.develop.web.domain.admin.team.mapper;

import com.develop.web.domain.admin.team.dto.RequsetTeamForAdminDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminTeamMapper {

    void addTeam(RequsetTeamForAdminDto requsetTeamForAdminDto);
    void deleteTeam(Integer teamId);
}
