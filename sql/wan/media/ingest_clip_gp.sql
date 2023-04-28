CREATE TABLE `ingest_clip_gp` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '인제스트 클립 그룹 ID',
    ingest_id INT NOT NULL COMMENT '인제스트 ID',
    p_id INT NOT NULL COMMENT '상위 그룹 ID'
) COMMENT '인제스트 클립 그룹';

ALTER TABLE `ingest_clip_gp` ADD FOREIGN KEY (p_id) REFERENCES `ingest_clip_gp`(id)