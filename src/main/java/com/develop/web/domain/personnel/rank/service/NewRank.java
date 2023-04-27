package com.develop.web.domain.personnel.rank.service;

import com.develop.web.domain.personnel.rank.dto.NewRankDto;
import com.develop.web.domain.personnel.rank.mapper.RankMapper;
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
