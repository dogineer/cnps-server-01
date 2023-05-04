package com.develop.web.domain.media.clip.mapper;

import com.develop.web.domain.media.clip.dto.ClipDto;
import com.develop.web.domain.media.ingest.dto.ResultRequestData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClipMapper {
    List<ClipDto> selectClipList();
    void insertClipData(ResultRequestData requestData);
}
