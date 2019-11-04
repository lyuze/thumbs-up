/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50724
Source Host           : 127.0.0.1:3306
Source Database       : thumbs-up

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-11-04 22:46:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别，0未知 1男 2女',
  `tel` varchar(16) DEFAULT '' COMMENT '电话',
  `email` varchar(32) DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(512) DEFAULT '' COMMENT '头像',
  `openid` varchar(64) DEFAULT '' COMMENT '微信openid',
  `qq_id` varchar(64) DEFAULT '' COMMENT 'qq的id',
  `role` tinyint(1) DEFAULT '0' COMMENT '角色 0未知',
  `city` varchar(32) DEFAULT '' COMMENT '城市',
  `experience` tinyint(2) DEFAULT '0' COMMENT '工作经验, 0:在校生 1:0年 2:1-2年 3:3-5年 4:5-10年 5:10年+',
  `skill` varchar(256) DEFAULT '' COMMENT '技能',
  `workday_start_time` time DEFAULT NULL COMMENT '工作日空闲开始时间',
  `workday_end_time` time DEFAULT NULL COMMENT '工作日空闲结束时间',
  `weekend_start_time` time DEFAULT NULL COMMENT '周末空闲结束时间',
  `weekend_end_time` time DEFAULT NULL COMMENT '周末空闲结束时间',
  `influence` int(11) DEFAULT '0' COMMENT '影响力',
  `like_num` int(11) DEFAULT '0' COMMENT '赞的数量',
  `introduce` varchar(512) DEFAULT '' COMMENT '个人简介',
  `status` tinyint(1) DEFAULT '1' COMMENT '用户状态，0已注销 1正常',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `liked_user_id` varchar(32) NOT NULL COMMENT '被点赞的用户id',
  `liked_post_id` varchar(32) NOT NULL COMMENT '点赞的用户id',
  `status` tinyint(1) DEFAULT '1' COMMENT '点赞状态，0取消，1点赞',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `liked_user_id` (`liked_user_id`),
  KEY `liked_post_id` (`liked_post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户点赞表';

-- ----------------------------
-- Records of user_like
-- ----------------------------
