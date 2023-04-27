package com.develop.web.domain.personnel.rank.mapper;

import com.develop.web.domain.personnel.rank.dto.NewRankDto;
import com.develop.web.domain.personnel.rank.dto.RankDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RankMapper {
    void insetNewRank(NewRankDto rankDto);
    List<RankDto> selectListRank();
}
