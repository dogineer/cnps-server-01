CREATE TABLE `folder_gp` (
      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '폴더 그룹 ID',
      team_id INT NOT NULL COMMENT '팀 ID',
      folder_id INT NOT NULL COMMENT '폴더 ID'
) COMMENT '폴더 팀 그룹';

ALTER TABLE `folder` ADD FOREIGN KEY (p_id) REFERENCES `folder`(id)