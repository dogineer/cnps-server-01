CREATE TABLE a_metadata (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '메타 ID',
    clip_uuid VARCHAR(36) NOT NULL UNIQUE KEY COMMENT '영상 uuid',
    create_ymd DATE NOT NULL DEFAULT (CURRENT_TIMESTAMP) COMMENT '생성날짜',
    file_path TEXT COMMENT '파일 경로',
    file_name VARCHAR(255) COMMENT '파일 이름',
    ext VARCHAR(4) COMMENT '확장자',
    width INT(32) COMMENT '가로',
    height INT(32) COMMENT '세로',
    format_name VARCHAR(32) COMMENT '포맷 이름',
    format_long_name VARCHAR(32) COMMENT '포맷 전체 이름',
    tags TEXT COMMENT '영상 태그 정보',
    duration INT(255) COMMENT '길이',
    size BIGINT COMMENT '용량'
) COMMENT '아카이브 메타데이터';