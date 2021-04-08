/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 127.0.0.1:3306
 Source Schema         : wxApp

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 08/04/2021 18:47:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `appid` varchar(255) NOT NULL COMMENT '微信小程序的appid',
  `secret` varchar(255) NOT NULL COMMENT '微信小程序的Secret',
  `token` varchar(255) DEFAULT NULL COMMENT '微信小程序消息服务器配置的token',
  `aes_key` varchar(255) DEFAULT NULL COMMENT '微信小程序消息服务器配置的EncodingAESKey',
  `msg_data_format` varchar(255) DEFAULT 'JSON' COMMENT '消息格式，XML或者JSON',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
