package com.develop.web.domain.admin.position.service;

import com.develop.web.domain.admin.position.dto.PosDetailDto;
import com.develop.web.domain.admin.position.dto.PosDto;
import com.develop.web.domain.admin.position.mapper.PosMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PosMapper posMapper;

    public void addPos(PosDto posDto){
        posMapper.insertPos(posDto);
    }

    public void removePos(Integer posId){
        posMapper.deletePos(posId);
    }

    public List<PosDetailDto> findPosList() {
        return posMapper.selectListPos();
    }
}
