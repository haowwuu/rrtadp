/*
Navicat MySQL Data Transfer

Source Server         : 47.92.100.40
Source Server Version : 50173
Source Host           : 47.92.100.40:3306
Source Database       : rrtgg

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-07-22 15:28:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for district_code
-- ----------------------------
DROP TABLE IF EXISTS `district_code`;
CREATE TABLE `district_code` (
  `id` mediumint(7) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` mediumint(7) unsigned NOT NULL DEFAULT '0' COMMENT '父级ID',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '层级',
  `area_code` bigint(12) unsigned NOT NULL DEFAULT '0' COMMENT '行政代码',
  `zip_code` mediumint(6) unsigned zerofill NOT NULL DEFAULT '000000' COMMENT '邮政编码',
  `city_code` char(6) NOT NULL DEFAULT '' COMMENT '区号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `short_name` varchar(50) NOT NULL DEFAULT '' COMMENT '简称',
  `merger_name` varchar(50) NOT NULL DEFAULT '' COMMENT '组合名',
  `pinyin` varchar(30) NOT NULL DEFAULT '' COMMENT '拼音',
  `lng` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '经度',
  `lat` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '维度',
  PRIMARY KEY (`id`),
  KEY `idx_lev` (`level`,`parent_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=714473 DEFAULT CHARSET=utf8 COMMENT='中国行政地区表';

-- ----------------------------
-- Table structure for rrt_ad
-- ----------------------------
DROP TABLE IF EXISTS `rrt_ad`;
CREATE TABLE `rrt_ad` (
  `id` char(16) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `content_url` varchar(45) DEFAULT NULL,
  `time_in_second` int(5) DEFAULT NULL,
  `owner` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rrt_media_device
-- ----------------------------
DROP TABLE IF EXISTS `rrt_media_device`;
CREATE TABLE `rrt_media_device` (
  `id` char(16) NOT NULL,
  `device_type` char(1) DEFAULT NULL,
  `device_status` char(1) DEFAULT NULL,
  `base_price` decimal(8,2) DEFAULT NULL,
  `key_words` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `play_time` datetime DEFAULT NULL,
  `play_frequency` int(3) DEFAULT NULL,
  `lng` decimal(12,9) DEFAULT NULL,
  `lat` decimal(12,9) DEFAULT NULL,
  `district_code` char(9) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `owner` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rrt_order
-- ----------------------------
DROP TABLE IF EXISTS `rrt_order`;
CREATE TABLE `rrt_order` (
  `id` char(16) NOT NULL,
  `ad_id` char(16) DEFAULT NULL,
  `device_id` char(16) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  `owner` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_company
-- ----------------------------
DROP TABLE IF EXISTS `user_company`;
CREATE TABLE `user_company` (
  `id` char(16) NOT NULL,
  `account` char(32) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `account_type` char(1) DEFAULT NULL,
  `account_state` char(1) DEFAULT NULL,
  `account_role` char(1) DEFAULT NULL,
  `company_name` char(64) DEFAULT NULL,
  `legal_person` char(32) DEFAULT NULL,
  `contact_person` char(32) DEFAULT NULL,
  `contact_phone` char(16) DEFAULT NULL,
  `office_phone` char(16) DEFAULT NULL,
  `district_code` char(9) DEFAULT NULL,
  `company_address` varchar(45) DEFAULT NULL,
  `certificate` char(32) DEFAULT NULL,
  `certiticate_front_url` varchar(45) DEFAULT NULL,
  `certificate_back_url` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_person
-- ----------------------------
DROP TABLE IF EXISTS `user_person`;
CREATE TABLE `user_person` (
  `id` char(16) NOT NULL,
  `account` char(32) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `account_type` char(1) DEFAULT NULL,
  `account_role` char(1) DEFAULT NULL,
  `account_state` char(1) DEFAULT NULL,
  `user_name` char(32) DEFAULT NULL,
  `nick_name` char(32) DEFAULT NULL,
  `phone` char(16) DEFAULT NULL,
  `email` char(32) DEFAULT NULL,
  `district_code` char(9) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `id_card` char(32) DEFAULT NULL,
  `id_card_front_url` varchar(45) DEFAULT NULL,
  `id_card_back_url` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
