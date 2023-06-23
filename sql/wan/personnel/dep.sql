CREATE TABLE `dept` (
      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '부서 ID',
      name VARCHAR(36) DEFAULT 'name' NOT NULL COMMENT '부서, 팀 이름',
      p_id INT NOT NULL DEFAULT '0' COMMENT '상위 부서 ID'
) COMMENT '부서';

ALTER TABLE `dept` ADD FOREIGN KEY (p_id) REFERENCES `dept`(id)

WITH RECURSIVE C AS (
    SELECT e.account, d.id, d.p_id, d.name, e.name AS member, CONCAT_WS(' > ',d3.name, d2.name, d.name, e.name) AS path
    FROM dept d
    INNER JOIN emp e ON d.id = e.dept_id
    LEFT JOIN dept d2 ON d.p_id = d2.id
    LEFT JOIN dept d3 ON d2.p_id = d3.id
) SELECT C.path FROM C WHERE C.account = #{account};

WITH RECURSIVE C AS (
  SELECT d.id, d.p_id, d.name, d.name AS path
  FROM dept d
  WHERE d.id = #{id}

  UNION ALL

  SELECT d.id, d.p_id, d.name, CONCAT(C.path, ' > ', d.name)
  FROM dept d
  INNER JOIN C ON d.p_id = C.id
)
SELECT path FROM C;

