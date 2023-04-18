CREATE TABLE metadata (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '영상 ID',
    create_ymd DATE NOT NULL DEFAULT (CURRENT_TIMESTAMP) COMMENT '생성날짜',
    file_name VARCHAR(255) COMMENT '파일 이름',
    width INT(32) COMMENT '가로',
    height INT(32) COMMENT '세로',
    format_name VARCHAR(32) COMMENT '포맷 이름',
    format_long_name VARCHAR(32) COMMENT '포맷 전체 이름',
    tags TEXT COMMENT '영상 태그 정보',
    duration INT(255) COMMENT '길이',
    size BIGINT COMMENT '용량',
    PRIMARY KEY (id)
);