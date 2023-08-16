package com.develop.web.domain.service.clip.service;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.clip.mapper.ClipMapper;
import com.develop.web.domain.service.page.dto.CriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClipDataListFetcher {
    private final ClipMapper clipMapper;

    public List<ClipDto> getClipList(CriteriaDto cri){
        return clipMapper.selectGetClipList(cri);
    }
}
