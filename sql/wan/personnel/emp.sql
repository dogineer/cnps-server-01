CREATE TABLE `emp`(
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '유저 ID',
     dept_id INT NOT NULL COMMENT '부서 ID',
     rank_id INT NOT NULL COMMENT '직급 ID',
     team_id INT NOT NULL DEFAULT 3 COMMENT '팀 ID',
     account VARCHAR(255) UNIQUE KEY  COMMENT '아이디',
     name VARCHAR(30) COMMENT '이름',
     birth DATE COMMENT '생년월일',
     gender BIT(1) NOT NULL COMMENT '성별',
     phone VARCHAR(11) COMMENT '전화번호',
     email VARCHAR(255) COMMENT '이메일',
     des VARCHAR(255) COMMENT '설명',
     joined_at DATETIME NOT NULL DEFAULT (CURRENT_TIMESTAMP) COMMENT '가입날짜',
     approved_at DATETIME COMMENT '승인날짜',
     access BIT(1) DEFAULT 0 NOT NULL COMMENT '접근',
     del_fl BIT(1) DEFAULT 0 NOT NULL COMMENT '삭제요청',
     password VARCHAR(255) COMMENT '비밀번호'
) COMMENT '고용주';

ALTER TABLE `emp` ADD FOREIGN KEY (team_id) REFERENCES `team`(id);
ALTER TABLE `emp` ADD FOREIGN KEY (rank_id) REFERENCES `rank`(id);
ALTER TABLE `emp` ADD FOREIGN KEY (dept_id) REFERENCES `dept`(id);

INSERT * FROM `emp` VALUES ()