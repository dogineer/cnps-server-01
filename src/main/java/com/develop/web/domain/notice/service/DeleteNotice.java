package com.develop.web.domain.notice.service;

import com.develop.web.domain.notice.mapper.NoticeMapper;
import com.develop.web.domain.notice.validation.FindNoticeChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNotice {
    private final NoticeMapper noticeMapper;
    private final FindNoticeChecker findNoticeChecker;

    public void noticeId(Integer noticeId) {
        findNoticeChecker.getNotice(noticeId);
        noticeMapper.deleteNotice(noticeId);
    }
}
