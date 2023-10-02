package com.develop.web.domain.admin.position.service;

import com.develop.web.domain.admin.position.mapper.PosMapper;
import com.develop.web.domain.admin.position.dto.PosDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PosListFetcherService {
    private final PosMapper posMapper;

    public List<PosDetailDto> findPosList() {
        return posMapper.selectListPos();
    }
}
