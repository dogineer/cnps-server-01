package com.develop.web.domain.admin.rank.service;

import com.develop.web.domain.admin.rank.mapper.RankMapper;
import com.develop.web.domain.admin.rank.dto.RankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankListFetcher {
    private final RankMapper rankMapper;

    public List<RankDto> getRankList() {
        return rankMapper.selectListRank();
    }
}
