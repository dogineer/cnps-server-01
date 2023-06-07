package com.develop.web.domain.notice.mapper;

import com.develop.web.domain.notice.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    void insertNotice(NoticeDto vo);

    NoticeDto selectNotice(Integer NoticeId);

    List<NoticeDto> selectNoticeList();

    void deleteNotice(Integer noticeId);
}
