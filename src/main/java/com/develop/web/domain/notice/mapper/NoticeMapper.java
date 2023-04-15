package com.develop.web.domain.notice.mapper;

import com.develop.web.domain.notice.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    void insertNotice(PostDto vo);

    List<PostDto> selectNoticeList();
}
