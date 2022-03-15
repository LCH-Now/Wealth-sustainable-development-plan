/*
 Navicat Premium Data Transfer

 Source Server         : LocalDataBase
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : dev

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 15/03/2022 10:34:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_happy_eight
-- ----------------------------
DROP TABLE IF EXISTS `t_happy_eight`;
CREATE TABLE `t_happy_eight`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键编号',
  `lottery_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '彩票期数',
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中奖号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_happy_eight
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('22a37dbf-51b7-4271-9e79-34094580a8d1', '35222919960702604X', '123456789', '宁荣荣', '00', '35222919960702604X', '13860377900');
INSERT INTO `t_user` VALUES ('60975ea6-173e-4558-9faf-b512fc67d085', '35222919960702603X', '123456789', '小舞', '00', '35222919960702603X', '13860378490');
INSERT INTO `t_user` VALUES ('65d0ef7e-014e-46e1-8777-d76302011851', '35222919960702602X', '123456789', '唐三', '01', '35222919960702602X', '18834678769');
INSERT INTO `t_user` VALUES ('6ef6f0d2-4d59-4144-b3f4-212181f63867', '35222919960702601X', '123456789', '刘春浩', '01', '35222919960702601X', '13123301728');

SET FOREIGN_KEY_CHECKS = 1;
