CREATE TABLE `folder` (
      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '폴더 ID',
      name VARCHAR(36) DEFAULT 'name' NOT NULL COMMENT '폴더 이름',
      p_id INT NOT NULL DEFAULT '0' COMMENT '상위 폴더 ID'
) COMMENT '폴더';

ALTER TABLE `folder` ADD FOREIGN KEY (p_id) REFERENCES `folder`(id)

/* 폴더 데이터 조회 */
WITH RECURSIVE C AS
   (SELECT id
    FROM folder
    WHERE id = ${folderId}
    UNION ALL
    SELECT f.id
    FROM folder f
        JOIN C rf ON f.p_id = rf.id)
    SELECT c.id     AS clip_id,
           c.ingest_id,
           i.title  AS `ingest_name`,
           c.team_id,
           t.name   AS `program_name`,
           c.folder_id,
           f.name   AS `folder_name`,
           c.convert_metadata_id,
           c.end_at AS ingest_at,
           a.*
    FROM clip c
             LEFT JOIN ingest i ON i.id = c.ingest_id
             LEFT JOIN team t ON t.id = c.team_id
             LEFT JOIN folder f ON f.id = c.folder_id
             LEFT JOIN convert_metadata a ON a.id = c.convert_metadata_id
    WHERE c.folder_id IN (SELECT id
                          FROM C)
ORDER BY c.end_at DESC;
