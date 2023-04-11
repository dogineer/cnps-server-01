CREATE TABLE ingest (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'PK 프로그램 코드',
    user_pk BIGINT COMMENT '유저 PK',
    create_ymd DATE NOT NULL DEFAULT (CURRENT_TIMESTAMP) COMMENT '생성날짜',
    title VARCHAR(255) COMMENT '제목',
    program VARCHAR(32) COMMENT '프로그램명',
    user VARCHAR(32) COMMENT '요청자',
    phone VARCHAR(11) COMMENT '전화번호',
    codec VARCHAR(32) COMMENT '코덱',
    del_fl BIT(1) DEFAULT 0 NOT NULL COMMENT '삭제요청',
    PRIMARY KEY (id)
);
