package com.develop.web.domain.media.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class IngestRequestData {
    private LocalDateTime createDate;   // 생성 날짜
    private String title;               // 제목
    private String program;             // 프로그램명
    private String program_code;        // 프로그램 코드
    private String name;                // 요청자
    private String phone;               // 전화번호
    private Codec codec;                // 요청코덱
    private int success;                // 성공 유무

    private MultipartFile files;         // 영상 데이타
}
