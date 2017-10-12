ALTER TABLE `rrt_media_device`
ADD COLUMN `play_id`  char(16) NULL COMMENT '设备播放id' AFTER `description`,
ADD COLUMN `foreign_id`  char(16) NULL COMMENT '设备在其他管理平台上的id' AFTER `play_id`,
ADD COLUMN `serial_number`  char(16) NULL COMMENT '设备硬件编号' AFTER `foreign_id`;
