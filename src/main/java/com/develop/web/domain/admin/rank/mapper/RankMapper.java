package com.develop.web.domain.admin.rank.mapper;

import com.develop.web.domain.admin.rank.dto.NewRankDto;
import com.develop.web.domain.admin.rank.dto.RankDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RankMapper {
    void insetNewRank(NewRankDto rankDto);
    List<RankDto> selectListRank();
}
