package com.develop.web.domain.service.clip.mapper;

import com.develop.web.domain.service.clip.dto.ClipDto;
import com.develop.web.domain.service.ingest.dto.ResultRequestData;
import com.develop.web.domain.service.page.dto.CriteriaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClipMapper {
    List<ClipDto> selectClipList();

    List<ClipDto> selectGetClipList(CriteriaDto cri);
    void insertClipData(ResultRequestData requestData);

    int selectClipCount();
}
