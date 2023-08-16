package com.develop.web.domain.admin.team.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminTeamMapper {

    void addTeam(String teamName, Integer teamType);
    void deleteTeam(Integer teamId);
}
