package com.develop.web.domain.notice.service;

import com.develop.web.domain.notice.mapper.NoticeMapper;
import com.develop.web.domain.notice.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostListFetcher {
    private final NoticeMapper noticeMapper;

    public PostListFetcher(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public void setPost(PostDto postDto){
        noticeMapper.insertNotice(postDto);
    }

    public List<PostDto> getPost(){
        return noticeMapper.selectNoticeList();
    }
}
