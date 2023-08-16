package com.develop.web.domain.service.ingest.service;

import com.develop.web.domain.service.clip.mapper.ClipMapper;
import com.develop.web.domain.service.ingest.dto.ResultRequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateClipPost {
    private final ClipMapper clipMapper;

    public void addClipPost(ResultRequestData requestData){
        clipMapper.insertClipData(requestData);
    }
}
