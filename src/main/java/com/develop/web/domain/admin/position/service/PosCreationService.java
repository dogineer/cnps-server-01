package com.develop.web.domain.admin.position.service;

import com.develop.web.domain.admin.position.dto.PosDto;
import com.develop.web.domain.admin.position.mapper.PosMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosCreationService {
    private final PosMapper posMapper;

    public void addPos(PosDto posDto){
        posMapper.insertPos(posDto);
    }
}
