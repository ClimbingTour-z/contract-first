/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : excel

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-31 15:02:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for compact
-- ----------------------------
DROP TABLE IF EXISTS `compact`;
CREATE TABLE `compact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_sign_year` varchar(255) NOT NULL,
  `item_coding` varchar(255) NOT NULL,
  `sale_person` varchar(255) NOT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `contract_amount` varchar(255) DEFAULT NULL,
  `invoice_amount_2015` varchar(255) DEFAULT NULL,
  `recovered_amount_2015` varchar(255) DEFAULT NULL,
  `invoice_amount_2016` varchar(255) DEFAULT NULL,
  `recovered_amount_date_2016` varchar(255) DEFAULT NULL,
  `recovered_amount_2016` varchar(255) DEFAULT NULL,
  `invoice_amount_2017` varchar(255) DEFAULT NULL,
  `recovered_amount_date_2017` varchar(255) DEFAULT NULL,
  `recovered_amount_2017` varchar(255) DEFAULT NULL,
  `invoice_amount_2018` varchar(255) DEFAULT NULL,
  `invoice_amount_date_2018` varchar(255) DEFAULT NULL,
  `recovered_amount_2018` varchar(255) DEFAULT NULL,
  `recovered_amount_date_2018` varchar(255) DEFAULT NULL,
  `due` varchar(255) DEFAULT NULL,
  `invoice_amount` varchar(255) DEFAULT NULL,
  `unbilled_amount` varchar(255) DEFAULT NULL,
  `receivable_total` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of compact
-- ----------------------------
INSERT INTO `compact` VALUES ('66', '2014.0', '2014-ZJYD-ZZJH-01', '田婧玉', '*****项目合同', '2000000.0', '500000.0', '300000.0', '200000.0', '2016-06-02', '150000.0', '50000.0', '2017-08-09', '150000.0', '2018-05-02', '500000.0', '2018-04-08', '600000.0', '50000.0', '1250000.0', '750000.0', '1200000.0');
INSERT INTO `compact` VALUES ('80', '2015.0', '2015-ZJYD-ZZJH-03', '陈臣臣', '*****项目合同', '3000000.0', '500000.0', '200000.0', '200000.0', '2016-06-02', '150000.0', '50000.0', '2017-08-09', '150000.0', '2018-05-02', '500000.0', '2018-04-08', '600000.0', '150000.0', '1250000.0', '1750000.0', '1100000.0');
INSERT INTO `compact` VALUES ('82', '2015.0', '2015-ZTYD-ZZJH-33', '茜茜', '*****项目合同', '3000000.0', '500000.0', '200000.0', '200000.0', '2016-06-02', '150000.0', '50000.0', '2017-08-09', '150000.0', '2018-05-02', '500000.0', '2018-04-08', '600000.0', '150000.0', '1250000.0', '1750000.0', '1100000.0');
INSERT INTO `compact` VALUES ('83', '1997.0', '1997-AGVC-WDXF-05', '萌萌', '*****合同', '5560000.0', '4000000.0', '3044150.0', '220450.0', '2016-01-02', '210000.0', '51000.0', '2017-12-10', '454000.0', '2018-06-04', '400000.0', '2018-06-09', '500000.0', '463300.0', '4671450.0', '888550.0', '4208150.0');
INSERT INTO `compact` VALUES ('84', '2013.0', '2013-ZJYD-ZZJH-04', '陈臣臣', '*****项目合同', '3000000.0', '500000.0', '200000.0', '200000.0', '2016-06-02', '150000.0', '50000.0', '2017-08-09', '150000.0', '2018-05-02', '500000.0', '2018-04-08', '600000.0', '150000.0', '1250000.0', '1750000.0', '1100000.0');
INSERT INTO `compact` VALUES ('85', '1997.0', '1997-ABVC-WRXF-02', '韩梅梅', '*****合同', '5560000.0', '4000000.0', '3044150.0', '220450.0', '2016-01-02', '210000.0', '51000.0', '2017-12-10', '454000.0', '2018-06-04', '400000.0', '2018-06-09', '500000.0', '463300.0', '4671450.0', '888550.0', '4208150.0');
INSERT INTO `compact` VALUES ('140', '2012.0', '2012-ZJYD-ZZJH-03', '韩梅梅', '*****项目合同', '3000000.0', '500000.0', '200000.0', '200000.0', '2016-06-02', '150000.0', '50000.0', '2017-08-09', '150000.0', '2018-05-02', '500000.0', '2018-04-08', '600000.0', '150000.0', '1250000.0', '1750000.0', '1100000.0');
INSERT INTO `compact` VALUES ('141', '1998.0', '1998-ABVC-WRXF-02', '王雷雷', '*****合同', '5560000.0', '4000000.0', '3044150.0', '220450.0', '2016-01-02', '210000.0', '51000.0', '2017-12-10', '454000.0', '2018-06-04', '400000.0', '2018-06-09', '500000.0', '463300.0', '4671450.0', '888550.0', '4208150.0');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Customer1` varchar(55) DEFAULT NULL,
  `Customer2` varchar(55) DEFAULT NULL,
  `Customer3` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '在', '这', '里');
INSERT INTO `customer` VALUES ('2', '我', '你', '他');
INSERT INTO `customer` VALUES ('3', '天', '地', '人');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456');
INSERT INTO `user` VALUES ('2', 'ceshi', 'ceshi');

-- ----------------------------
-- Table structure for weather
-- ----------------------------
DROP TABLE IF EXISTS `weather`;
CREATE TABLE `weather` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taizhan_num` varchar(255) DEFAULT NULL,
  `tem` varchar(255) DEFAULT NULL,
  `hum` varchar(255) DEFAULT NULL,
  `pa` varchar(255) DEFAULT NULL,
  `rain` varchar(255) DEFAULT NULL,
  `win_dir` varchar(255) DEFAULT NULL,
  `win_sp` varchar(255) DEFAULT NULL,
  `dateStr` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weather
-- ----------------------------
INSERT INTO `weather` VALUES ('1', 'A0001', '23', '321', '122', '232', '232', '321', '2017-03-08 00:00:00', '2017-03-08 00:00:00');
INSERT INTO `weather` VALUES ('2', 'A0001', '32', '231', '323', '323', '663', '322', '2017-03-08 00:00:00', '2017-03-08 00:00:00');
INSERT INTO `weather` VALUES ('3', 'A0001', '22', '321', '324', '323', '322', '322', '2017-03-08 00:00:00', '2017-03-08 00:00:00');
INSERT INTO `weather` VALUES ('4', 'A0001', '32', '323', '321', '322', '322', '324', '2017-03-08 00:00:00', '2017-03-08 00:00:00');
INSERT INTO `weather` VALUES ('5', 'A0001', '32', '234', '321', '322', '325', '421', '2017-03-08 00:00:00', '2017-03-08 00:00:00');
