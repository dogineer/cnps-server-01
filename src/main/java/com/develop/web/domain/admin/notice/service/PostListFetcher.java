package com.develop.web.domain.admin.notice.service;

import com.develop.web.domain.admin.notice.dto.NoticeDto;
import com.develop.web.domain.admin.notice.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostListFetcher {
    private final NoticeMapper noticeMapper;

    public PostListFetcher(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public void setPost(NoticeDto noticeDto){
        noticeMapper.insertNotice(noticeDto);
    }

    public List<NoticeDto> getPost(){
        return noticeMapper.selectNoticeList();
    }
}
