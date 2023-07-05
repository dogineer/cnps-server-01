package com.develop.web.domain.media.clip.service;

import com.develop.web.domain.media.clip.dto.ClipDto;
import com.develop.web.domain.media.clip.mapper.ClipMapper;
import com.develop.web.domain.page.dto.CriteriaDto;
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
