package com.develop.web.domain.media.groupclip.mapper;

import com.develop.web.domain.media.upload.dto.IngestRequestData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupClipsMapper {
    void selectFolderIncludeClips(IngestRequestData ingestRequestData);
}
