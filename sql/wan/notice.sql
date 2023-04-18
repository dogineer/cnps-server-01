CREATE TABLE notice (
    id INT NOT NULL AUTO_INCREMENT COMMENT '게시물번호',
    emp_id INT NOT NULL COMMENT '작성자 PK',
    writer VARCHAR(32) COMMENT '게시글의 작성자',
    title text COMMENT '게시글의 제목',
    content text COMMENT '게시글의 내용',
    PRIMARY KEY (id)
) COMMENT '공지사항';

ALTER TABLE `notice` ADD FOREIGN KEY (emp_id) REFERENCES `emp`(id)
