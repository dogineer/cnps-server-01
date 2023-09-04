CREATE TABLE `folder_gp` (
      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '폴더 그룹 ID',
      team_id INT NOT NULL COMMENT '팀 ID',
      folder_id INT NOT NULL COMMENT '폴더 ID'
) COMMENT '폴더 팀 그룹';

ALTER TABLE `folder` ADD FOREIGN KEY (p_id) REFERENCES `folder`(id)

select
    c.id as clip_id,
    c.ingest_id,
    i.title as `ingest_name`,
    c.team_id,
    t.name as `team_name`,
    c.folder_id,
    f.name as `folder_name`,
    c.archive_metadata_id,
    a.* from clip c
left join ingest i on i.id = c.ingest_id
left join team t on t.id = c.team_id
left join folder f on f.id = c.folder_id
left join archive_metadata a on a.id = c.archive_metadata_id

where c.folder_id = 4
