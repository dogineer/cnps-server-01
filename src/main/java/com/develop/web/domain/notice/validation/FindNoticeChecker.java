package com.develop.web.domain.notice.validation;

import com.develop.web.domain.notice.dto.NoticeDto;
import com.develop.web.domain.notice.mapper.NoticeMapper;
import com.develop.web.global.exception.code.NoticeErrorCode;
import com.develop.web.global.exception.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindNoticeChecker {
    private final NoticeMapper noticeMapper;

    public void getNotice(Integer noticeId) {
        NoticeDto isNotice = noticeMapper.selectNotice(noticeId);

        if (isNotice == null) {
            throw new CustomException(NoticeErrorCode.NOTICE_NO_DATA_AVAILABLE);
        }
    }
}
