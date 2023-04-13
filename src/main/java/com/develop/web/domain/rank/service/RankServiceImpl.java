package com.develop.web.domain.rank.service;

import com.develop.web.domain.rank.mapper.RankMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RankServiceImpl implements RankService {
    private final RankMapper rankMapper;

    public RankServiceImpl(RankMapper rankMapper) {
        this.rankMapper = rankMapper;
    }

    @Override
    public List<Map<Integer, String>> getList() {
        return rankMapper.queryListRank();
    }
}
