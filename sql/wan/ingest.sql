CREATE TABLE ingest (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'PK 프로그램 코드',
    f_member_id INT NOT NULL COMMENT '멤버 id' AS,
    create_at DATETIME NOT NULL DEFAULT (CURRENT_TIMESTAMP) COMMENT '생성날짜',
    title VARCHAR(255) COMMENT '제목',
    program VARCHAR(32) COMMENT '프로그램명',
    name VARCHAR(32) COMMENT '요청자',
    phone VARCHAR(11) COMMENT '전화번호',
    codec VARCHAR(32) COMMENT '코덱',
    del_fl BIT(1) DEFAULT 0 NOT NULL COMMENT '삭제요청'
) COMMENT '인제스트 목록';

ALTER TABLE `ingest` ADD FOREIGN KEY (f_member_id) REFERENCES `emp`(id)