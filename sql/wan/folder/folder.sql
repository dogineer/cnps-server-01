CREATE TABLE `folder` (
      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '폴더 ID',
      name VARCHAR(36) DEFAULT 'name' NOT NULL COMMENT '폴더 이름',
      p_id INT NOT NULL DEFAULT '0' COMMENT '상위 폴더 ID'
) COMMENT '폴더';

ALTER TABLE `folder` ADD FOREIGN KEY (p_id) REFERENCES `folder`(id)

/* 폴더 데이터 조회 */
select i.*
from clip_group c
join ingest i on c.clip_id = i.id
join folder f on c.folder_id = f.id
where f.id = ${f_id}