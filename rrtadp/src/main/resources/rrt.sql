

CREATE TABLE `rrt`.`user_pserson` (
		  `id` CHAR(16) NOT NULL,
		  `account` CHAR(32) NULL,
		  `password` CHAR(32) NULL,
		  `description` VARCHAR(45) NULL,
		  `account_type` CHAR(1) NULL,
		  `account_role` CHAR(1) NULL, 
          `account_state` CHAR(1) NULL,
		  `user_name` CHAR(32) NULL,
		  `nick_name` CHAR(32) NULL,
		  `phone` CHAR(16) NULL,
		  `email` CHAR(32) NULL,
		  `address` VARCHAR(45) NULL,
		  `id_card` CHAR(32) NULL,
		  `id_card_front_url` VARCHAR(45) NULL,
		  `id_card_back_url` VARCHAR(45) NULL,
		  `create_time` DATETIME NULL,
		  `update_time` DATETIME NULL,
		  PRIMARY KEY (`id`));

CREATE TABLE `rrt`.`user_company` (
		  `id` CHAR(16) NOT NULL,
		  `account` CHAR(32) NULL,
		  `password` CHAR(32) NULL,
		  `description` VARCHAR(45) NULL,
		  `account_type` CHAR(1) NULL,
          `account_state` CHAR(1) NULL,        
		  `account_role` CHAR(1) NULL,
		  `company_name` CHAR(64) NULL,
		  `legal_person` CHAR(32) NULL,
		  `contact_person` CHAR(32) NULL,
		  `office_phone` CHAR(16) NULL,
		  `company_address` VARCHAR(45) NULL,
		  `certificate` CHAR(32) NULL,
		  `certiticate_front_url` VARCHAR(45) NULL,
		  `certificate_back_url` VARCHAR(45) NULL,
		  `create_time` DATETIME NULL,
		  `update_tiem` DATETIME NULL,
		  PRIMARY KEY (`id`));

CREATE TABLE `rrt`.`rrt_media_device` (
  `id` CHAR(16) NOT NULL,
  `device_id` CHAR(32) NULL,
  `device_type` CHAR(1) NULL,
  `device_owner` CHAR(32) NULL,
  `base_price` DECIMAL(8,2) NULL,
  `key_word` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `longitude` DECIMAL(12,9) NULL,
  `latitude` DECIMAL(12,9) NULL,
  `address` VARCHAR(45) NULL,
  `create_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `rrt`.`rrt_ad` (
  `id` CHAR(16) NOT NULL,
  `title` VARCHAR(45) NULL,
  `type` CHAR(1) NULL,
  `content` VARCHAR(45) NULL,
  `content_url` VARCHAR(45) NULL,
  `play_time` DATETIME NULL,
  `play_frequency` INT NULL,
  `create_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`));
  
  
  