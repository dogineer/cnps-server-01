package com.develop.web.domain.personnel.rank.service;

import com.develop.web.domain.personnel.rank.dto.RankDto;
import com.develop.web.domain.personnel.rank.mapper.RankMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankListFetcher {
    private final RankMapper rankMapper;

    public RankListFetcher(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }
    public List<RankDto> getList() {
        return rankMapper.selectListRank();
    }
}
