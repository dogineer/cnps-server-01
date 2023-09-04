CREATE TABLE clip (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '컨버팅 클립 ID',
    ingest_id INT NOT NULL COMMENT '인제스트 ID',
    team_id INT NOT NULL COMMENT '팀 ID',
    folder_id INT DEFAULT NULL COMMENT '폴더 ID',
    e_metadata_id INT DEFAULT NULL COMMENT '편집본 메타 ID',
    archive_metadata_id INT NOT NULL COMMENT '원본 메타 ID',
    convert_metadata_id INT NOT NULL COMMENT '변환 메타 ID',
    ingest_at DATETIME DEFAULT NULL COMMENT '인제스트 요청 날짜',
    end_at DATETIME DEFAULT NULL COMMENT '인제스트 완료 여부',
    del_fl BIT(1) DEFAULT 0 NOT NULL COMMENT '삭제 요청'
) COMMENT '클립 목록';

ALTER TABLE `clip` ADD FOREIGN KEY (team_id) REFERENCES `team`(id);
ALTER TABLE `clip` ADD FOREIGN KEY (folder_id) REFERENCES `folder`(id);
ALTER TABLE `clip` ADD FOREIGN KEY (archive_metadata_id) REFERENCES `archive_metadata`(id);
ALTER TABLE `clip` ADD FOREIGN KEY (convert_metadata_id) REFERENCES `convert_metadata`(id);