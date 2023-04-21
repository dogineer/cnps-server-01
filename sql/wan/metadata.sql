CREATE TABLE metadata (
    id VARCHAR(36) NOT NULL AUTO_INCREMENT COMMENT '영상 ID',
    p_id INT NOT NULL DEFAULT '0' COMMENT '속해있는 게시물 ID'
    create_ymd DATE NOT NULL DEFAULT (CURRENT_TIMESTAMP) COMMENT '생성날짜',
    file_name VARCHAR(255) COMMENT '파일 이름',
    ext VARCHAR(4) COMMENT '확장자',
    width INT(32) COMMENT '가로',
    height INT(32) COMMENT '세로',
    format_name VARCHAR(32) COMMENT '포맷 이름',
    format_long_name VARCHAR(32) COMMENT '포맷 전체 이름',
    tags TEXT COMMENT '영상 태그 정보',
    duration INT(255) COMMENT '길이',
    size BIGINT COMMENT '용량',
    PRIMARY KEY (id)
);

ALTER TABLE `metadata` ADD FOREIGN KEY (p_id) REFERENCES `ingest`(id);