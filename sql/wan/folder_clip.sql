CREATE TABLE `folder_clip` (
      folder_id INT NOT NULL COMMENT '폴더 ID',
      clip_id INT NOT NULL COMMENT '폴더 클립 ID',
) COMMENT '폴더 클립 그룹';

ALTER TABLE `folder` ADD FOREIGN KEY (folder_id) REFERENCES `folder`(id)
ALTER TABLE `ingest` ADD FOREIGN KEY (clip_id) REFERENCES `ingest`(id)

/* 폴더 데이터 조회 */
select i.*
from clip_group c
join ingest i on c.clip_id = i.id
join folder f on c.folder_id = f.id
where f.id = ${f_id}