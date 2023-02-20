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
DROP TABLE IF EXISTS wx_mp_config;
CREATE TABLE `wx_mp_config` (
  `appid` varchar(255) NOT NULL COMMENT '微信公众号的appid',
  `secret` varchar(255) NOT NULL COMMENT '微信公众号的Secret',
  `token` varchar(255) DEFAULT NULL COMMENT '微信公众号消息服务器配置的token',
  `aes_key` varchar(255) DEFAULT NULL COMMENT '微信公众号消息服务器配置的EncodingAESKey',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
