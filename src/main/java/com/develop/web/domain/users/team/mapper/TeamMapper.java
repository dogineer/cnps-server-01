package com.develop.web.domain.users.team.mapper;

import com.develop.web.domain.users.team.dto.TeamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<TeamDto> selectBelongTeamList(Integer teamId, Integer rankId);
    List<TeamDto> selectTeamList();
    List<TeamDto> selectTeamTypeList();
}
