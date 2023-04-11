CREATE TABLE board (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '게시물번호',
    writer VARCHAR(32) COMMENT '게시글의 작성자',
    title VARCHAR(255) COMMENT '게시글의 제목',
    content VARCHAR(2000) COMMENT '게시글의 내용',
    PRIMARY KEY (id)
);
