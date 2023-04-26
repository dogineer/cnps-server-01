package com.develop.web.domain.personnel.team.mapper;

import com.develop.web.domain.personnel.team.dto.TeamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<TeamDto> selectTeamList();
}
