package com.develop.web.domain.admin.notice.dto;

import lombok.Data;

@Data
public class NoticeDto {

    private Integer id;         // 게시물고유번호
    private Integer userId;      // 작성자ID
    private String writer;      // 작성자
    private String title;       // 제목
    private String content;     // 내용

}
