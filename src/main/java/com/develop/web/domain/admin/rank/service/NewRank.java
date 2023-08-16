package com.develop.web.domain.admin.rank.service;

import com.develop.web.domain.admin.rank.dto.NewRankDto;
import com.develop.web.domain.admin.rank.mapper.RankMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewRank {
    private RankMapper rankMapper;

    public void setNewRank(NewRankDto rankDto){
        rankMapper.insetNewRank(rankDto);
    }
}
