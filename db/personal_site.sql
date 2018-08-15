/*
Navicat MySQL Data Transfer

Source Server         : mine
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : personal_site

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-15 10:24:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for atou_course
-- ----------------------------
DROP TABLE IF EXISTS `atou_course`;
CREATE TABLE `atou_course` (
  `id` bigint(20) unsigned NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atou_course
-- ----------------------------
INSERT INTO `atou_course` VALUES ('23465', null, null, 'guitar');

-- ----------------------------
-- Table structure for atou_course_content
-- ----------------------------
DROP TABLE IF EXISTS `atou_course_content`;
CREATE TABLE `atou_course_content` (
  `id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atou_course_content
-- ----------------------------

-- ----------------------------
-- Table structure for atou_course_index
-- ----------------------------
DROP TABLE IF EXISTS `atou_course_index`;
CREATE TABLE `atou_course_index` (
  `id` bigint(20) unsigned NOT NULL,
  `parent_id` bigint(20) unsigned DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atou_course_index
-- ----------------------------
INSERT INTO `atou_course_index` VALUES ('2134', '23465', null, null, 'ergfhrdf');
INSERT INTO `atou_course_index` VALUES ('3456', '2134', null, null, 'qwertryj');
INSERT INTO `atou_course_index` VALUES ('2344565', '2134', null, null, '哦哦');
INSERT INTO `atou_course_index` VALUES ('478158791276232704', '2344565', null, null, 'ewrth');
INSERT INTO `atou_course_index` VALUES ('478158805578809344', '478158791276232704', null, null, 'wertu');
INSERT INTO `atou_course_index` VALUES ('478158823253606400', '478158805578809344', null, null, 'etykuyutytdf');
INSERT INTO `atou_course_index` VALUES ('478158838831251456', '2344565', null, null, 'dergthj');

-- ----------------------------
-- Table structure for atou_sign_in_record
-- ----------------------------
DROP TABLE IF EXISTS `atou_sign_in_record`;
CREATE TABLE `atou_sign_in_record` (
  `id` bigint(20) unsigned NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `gmt_end` datetime DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atou_sign_in_record
-- ----------------------------

-- ----------------------------
-- Table structure for atou_statistics
-- ----------------------------
DROP TABLE IF EXISTS `atou_statistics`;
CREATE TABLE `atou_statistics` (
  `id` bigint(20) unsigned NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `course_id` bigint(20) unsigned DEFAULT NULL,
  `total_time` bigint(20) unsigned DEFAULT NULL COMMENT '存毫秒数',
  `total_click` int(10) unsigned DEFAULT NULL COMMENT '总点击',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atou_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for atou_user
-- ----------------------------
DROP TABLE IF EXISTS `atou_user`;
CREATE TABLE `atou_user` (
  `id` bigint(20) unsigned NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `wxid` varchar(255) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `total_time` bigint(20) DEFAULT NULL,
  `total_click` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atou_user
-- ----------------------------
INSERT INTO `atou_user` VALUES ('123', null, null, 'qwdefdg', null, null, '11');

-- ----------------------------
-- Table structure for atou_user_course
-- ----------------------------
DROP TABLE IF EXISTS `atou_user_course`;
CREATE TABLE `atou_user_course` (
  `id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atou_user_course
-- ----------------------------

-- ----------------------------
-- Table structure for atou_user_progress
-- ----------------------------
DROP TABLE IF EXISTS `atou_user_progress`;
CREATE TABLE `atou_user_progress` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of atou_user_progress
-- ----------------------------
