package com.develop.web.domain.team.mapper;

import com.develop.web.domain.team.dto.TeamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<TeamDto> selectTeamList();
}
