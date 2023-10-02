package com.develop.web.domain.admin.position.service;

import com.develop.web.domain.admin.position.mapper.PosMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosDeletionService {
    private final PosMapper posMapper;

    public void removePos(Integer posId){
        posMapper.deletePos(posId);
    }
}
