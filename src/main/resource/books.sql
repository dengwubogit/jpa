/*
Navicat MySQL Data Transfer

Source Server         : SingDog
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : jpa

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-04-13 17:36:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('1', '55', 'java', '2');
INSERT INTO `books` VALUES ('2', '78', 'java8', '2');
INSERT INTO `books` VALUES ('3', '60', '.net', '4');
INSERT INTO `books` VALUES ('4', '58.5', '.php', '5');
INSERT INTO `books` VALUES ('5', '92', 'ssh', '2');
INSERT INTO `books` VALUES ('6', '70', 'c#', '6');
INSERT INTO `books` VALUES ('7', '60', 'java7', '6');
INSERT INTO `books` VALUES ('8', '60', 'javascript', '2');
INSERT INTO `books` VALUES ('9', '32', 'jquery', '4');
INSERT INTO `books` VALUES ('10', '55', 'erlang', '3');
INSERT INTO `books` VALUES ('11', '50', 'golang', '7');
INSERT INTO `books` VALUES ('12', '45', 'solr', '7');
