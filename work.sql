/*
Navicat MySQL Data Transfer

Source Server         : Wang
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : work

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2016-08-19 15:55:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `image_change`
-- ----------------------------
DROP TABLE IF EXISTS `image_change`;
CREATE TABLE `image_change` (
  `C_ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMAGE_ID` int(11) DEFAULT NULL,
  `CHANGEB_NAME` varchar(512) DEFAULT NULL COMMENT '修改前，图片描述',
  `CHANGE_CONTENT` varchar(512) DEFAULT NULL COMMENT '修改后图片描述',
  `CHANGEB_CLASSIFY` int(11) DEFAULT NULL COMMENT '修改前类别ID',
  `CHANGE_CLASSIFY` int(11) DEFAULT NULL COMMENT '修改后类别ID',
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `PERMISSION` int(11) NOT NULL,
  `CREATE_TIME` date NOT NULL,
  `EDITOR_USER_NAME` varchar(32) NOT NULL,
  `EDITOR_USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image_change
-- ----------------------------

-- ----------------------------
-- Table structure for `image_classify`
-- ----------------------------
DROP TABLE IF EXISTS `image_classify`;
CREATE TABLE `image_classify` (
  `IMAGE_CLASSIFY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMAGE_CLASSIFY_NAME` varchar(32) NOT NULL,
  `IMAGE_CLASSIFY_DESPRETION` varchar(512) DEFAULT NULL,
  `CREATE_TIME` date NOT NULL,
  `EDITOR_USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`IMAGE_CLASSIFY_ID`),
  KEY `EDITOR_USER_ID` (`EDITOR_USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image_classify
-- ----------------------------
INSERT INTO `image_classify` VALUES ('1', '风景', null, '2016-05-29', '1');
INSERT INTO `image_classify` VALUES ('2', '美女', null, '2016-05-29', '1');

-- ----------------------------
-- Table structure for `image_info`
-- ----------------------------
DROP TABLE IF EXISTS `image_info`;
CREATE TABLE `image_info` (
  `IMAGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMAGE_DESPRETION` varchar(32) DEFAULT NULL,
  `IMAGE_CLASSIFY_ID` int(11) NOT NULL,
  `IMAGE_ADDRESS` varchar(128) NOT NULL,
  `IMAGE_RANK` int(11) NOT NULL,
  `IMAGE_FORMAT` varchar(32) NOT NULL,
  `CREATE_TIME` date NOT NULL,
  `EDITOR_USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`IMAGE_ID`),
  KEY `IMAGE_CLASSIFY_ID` (`IMAGE_CLASSIFY_ID`),
  CONSTRAINT `image_info_ibfk_1` FOREIGN KEY (`IMAGE_CLASSIFY_ID`) REFERENCES `image_classify` (`IMAGE_CLASSIFY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image_info
-- ----------------------------
INSERT INTO `image_info` VALUES ('1', '', '2', '/upload/Images/20160529/0000001.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('2', '', '2', '/upload/Images/20160529/0000002.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('3', '', '2', '/upload/Images/20160529/0000003.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('4', '', '2', '/upload/Images/20160529/0000004.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('5', '', '2', '/upload/Images/20160529/0000005.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('6', '', '2', '/upload/Images/20160529/0000006.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('7', '', '2', '/upload/Images/20160529/0000007.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('8', '', '2', '/upload/Images/20160529/0000008.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('9', '', '2', '/upload/Images/20160529/0000009.jpg', '1', '.jpg', '2016-05-29', '1');
INSERT INTO `image_info` VALUES ('10', '', '2', '/upload/Images/20160529/0000010.jpg', '1', '.jpg', '2016-05-29', '1');

-- ----------------------------
-- Table structure for `image_log`
-- ----------------------------
DROP TABLE IF EXISTS `image_log`;
CREATE TABLE `image_log` (
  `IMAGE_LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` int(11) NOT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `CREATE_TIME` date DEFAULT NULL,
  `EDITOR_USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`IMAGE_LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image_log
-- ----------------------------
INSERT INTO `image_log` VALUES ('1', '0', null, '2016-05-29', '1');

-- ----------------------------
-- Table structure for `image_query_info`
-- ----------------------------
DROP TABLE IF EXISTS `image_query_info`;
CREATE TABLE `image_query_info` (
  `QUERY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMAGE_ UPLOADING _ADDRESS` varchar(512) DEFAULT NULL,
  `CREATE_TIME` date DEFAULT NULL,
  `EDITOR_USER_ID` int(11) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `QUERY_TIME` double DEFAULT NULL,
  PRIMARY KEY (`QUERY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image_query_info
-- ----------------------------
INSERT INTO `image_query_info` VALUES ('1', '/upload/queryImages/1.jpg', '2016-05-29', '1', null, null);
INSERT INTO `image_query_info` VALUES ('2', '/upload/queryImages/2.jpg', '2016-05-29', '1', null, null);

-- ----------------------------
-- Table structure for `image_result_info`
-- ----------------------------
DROP TABLE IF EXISTS `image_result_info`;
CREATE TABLE `image_result_info` (
  `RESULT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUERY_ID` int(11) DEFAULT NULL,
  `IMAGE_ID` int(11) DEFAULT NULL,
  `SIMILARITY_RANK` int(11) DEFAULT NULL,
  PRIMARY KEY (`RESULT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image_result_info
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(32) NOT NULL,
  `PERMISSION` int(1) NOT NULL,
  `USER_ PASSWORD` varchar(32) CHARACTER SET latin1 NOT NULL,
  `EMAIL` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `CREATE_DATE` date NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', '2', '123456', '', '', '2016-05-29');

-- ----------------------------
-- View structure for `image_class_view`
-- ----------------------------
DROP VIEW IF EXISTS `image_class_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `image_class_view` AS select `image_classify`.`IMAGE_CLASSIFY_ID` AS `IMAGE_CLASSIFY_ID`,`image_classify`.`IMAGE_CLASSIFY_NAME` AS `IMAGE_CLASSIFY_NAME`,count(`image_info`.`IMAGE_ID`) AS `NUM` from (`image_classify` left join `image_info` on((`image_classify`.`IMAGE_CLASSIFY_ID` = `image_info`.`IMAGE_CLASSIFY_ID`))) group by `image_classify`.`IMAGE_CLASSIFY_ID`,`image_classify`.`IMAGE_CLASSIFY_NAME` ;

-- ----------------------------
-- View structure for `image_upload_view`
-- ----------------------------
DROP VIEW IF EXISTS `image_upload_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `image_upload_view` AS select distinct `image_info`.`EDITOR_USER_ID` AS `EDITOR_USER_ID`,`user_info`.`USER_NAME` AS `USER_NAME`,count(0) AS `NUM` from (`image_info` join `user_info`) where (`image_info`.`EDITOR_USER_ID` = `user_info`.`USER_ID`) group by `image_info`.`EDITOR_USER_ID` ;
