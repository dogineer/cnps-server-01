package com.develop.web.domain.media.vo;

import lombok.Data;

@Data
public class IngestRequestVo {
    private String title;       // 제목
    private String program;     // 프로그램명
    private Long program_code;  // 프로그램 코드
    private String name;        // 요청자
    private String phone;       // 전화번호
    private String Codec;       // 요청코덱
}
