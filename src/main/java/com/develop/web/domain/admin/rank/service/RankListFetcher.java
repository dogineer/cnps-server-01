package com.develop.web.domain.admin.rank.service;

import com.develop.web.domain.admin.rank.mapper.RankMapper;
import com.develop.web.domain.admin.rank.dto.RankDto;
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
