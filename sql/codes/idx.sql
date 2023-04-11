CREATE TABLE idx (
     uuid BINARY(16) NOT NULL PRIMARY KEY,
     user_id INT NOT NULL COMMENT '유저 ID',
     dept_id INT NOT NULL COMMENT '부서 ID',
     team_id INT NOT NULL COMMENT '팀 ID',
     rank_id INT NOT NULL COMMENT '직급 ID',
     job_id INT NOT NULL COMMENT '하는 일 ID'
) COMMENT '회원 모든 idx 정보';

