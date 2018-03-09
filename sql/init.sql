/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : mc_user

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2018-03-09 11:16:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_sysmgr_aclrequesttype
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_aclrequesttype`;
CREATE TABLE `tbl_sysmgr_aclrequesttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) NOT NULL COMMENT '请求类型',
  `pronoun` varchar(200) NOT NULL COMMENT '请求类型代码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `pronoun` (`pronoun`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_aclrequesttype
-- ----------------------------
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('1', '增加', 'add', '2016-07-12 22:25:27', '2016-07-12 22:25:30');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('2', '读取', 'query', '2016-07-13 15:10:13', '2016-07-13 15:10:18');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('3', '更新', 'update', '2016-07-13 15:10:24', '2016-07-13 15:10:20');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('4', '删除', 'delete', '2016-07-13 15:10:27', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('5', '开通', 'open', '2016-07-13 15:10:27', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('6', '关闭', 'close', '2016-07-13 15:10:27', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('7', '导出', 'export', '2016-07-13 15:10:27', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('8', '详情', 'detail', '2016-07-13 15:10:27', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('9', '审批', 'approval', '2016-07-13 15:10:27', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('10', '退回', 'back', '2016-07-13 15:10:27', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('11', '签收', 'sign', '2016-07-13 15:10:22', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('12', '关联', 'connect', '2016-07-13 15:10:22', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('13', '批量开通', 'openAll', '2016-07-13 15:10:22', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('14', '启用', 'start', '2016-07-13 15:10:22', '2016-07-13 15:10:22');
INSERT INTO `tbl_sysmgr_aclrequesttype` VALUES ('15', '重置', 'reset', '2016-07-13 15:10:22', '2016-07-13 15:10:22');

-- ----------------------------
-- Table structure for tbl_sysmgr_aclresources
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_aclresources`;
CREATE TABLE `tbl_sysmgr_aclresources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(200) NOT NULL COMMENT '资源地址type=s时，url为前端路径',
  `type` varchar(50) NOT NULL COMMENT '资源类型M-模块S-子模块R-CRUD请求',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `pronoun` varchar(50) NOT NULL COMMENT '资源代码',
  `parent_id` int(11) DEFAULT NULL COMMENT '请求类型',
  `request_type_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2202 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_aclresources
-- ----------------------------
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('2', 'pm', 'M', '人员管理', 'pm', null, null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('5', 'system', 'M', '系统设置', 'sm', null, null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('200', 'saferList', 'S', '安全员列表', 'pm_so', '2', null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('201', 'employeeList', 'S', '员工列表', 'pm_emp', '2', null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('202', 'partmentManage', 'S', '员工岗位管理', 'pm_post', '2', null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('203', 'saferOrganization', 'S', '安全员组织机构', 'pm_org', '2', null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('204', 'cityRelation', 'S', '自然市管理', 'pm_city', '2', null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('500', 'modifyPwd', 'S', '修改密码', 'sm_password', '5', null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('501', 'resetPwd', 'S', '重置密码', 'sm_password', '5', null, null, null, '2');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('2000', '/orgCitys', 'R', '分页自然城市管理列表', 'orgCitys', '204', '2', null, null, '1600');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('2001', '/orgCity', 'R', '新增组织机构与城市关系', 'cityOrg', '204', '12', null, null, '1601');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('2200', '/employee/resetPwd/{employeeNumber}', 'R', '后台重置密码', 'resetPwd', '501', '15', null, null, '2200');
INSERT INTO `tbl_sysmgr_aclresources` VALUES ('2201', '/updatePassword', 'R', '后台修改密码', 'updatePassword', '500', '3', null, null, '2201');

-- ----------------------------
-- Table structure for tbl_sysmgr_aclrole
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_aclrole`;
CREATE TABLE `tbl_sysmgr_aclrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(200) NOT NULL COMMENT '角色ID',
  `pronoun` varchar(200) NOT NULL COMMENT '角色代名词',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `user_number` int(11) DEFAULT NULL COMMENT '员工人数',
  `department_flag` varchar(20) DEFAULT NULL COMMENT '部门标示0-初始化 1-部门角色',
  `response_user_Id` int(20) DEFAULT NULL COMMENT '岗位负责人',
  `status` int(11) DEFAULT '0' COMMENT '角色状态0-正常  1 删除',
  `operateid` int(11) DEFAULT NULL COMMENT '操作人id',
  `operatetime` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_pronoun` (`role_name`,`pronoun`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_aclrole
-- ----------------------------
INSERT INTO `tbl_sysmgr_aclrole` VALUES ('1', '商户代表', 'SHDB', '2017-02-24 13:45:15', '2017-02-23 11:06:48', '0', '01', '0', '0', null, null);
INSERT INTO `tbl_sysmgr_aclrole` VALUES ('2', '安全员', 'AQU', '2017-02-24 13:45:24', '2017-02-24 18:46:51', '0', '01', null, '0', null, null);
INSERT INTO `tbl_sysmgr_aclrole` VALUES ('3', '商户后台', 'SHHT', '2017-02-24 13:45:27', '2017-02-24 18:46:54', '0', '01', null, '1', '18', '2017-02-24 16:06:26');
INSERT INTO `tbl_sysmgr_aclrole` VALUES ('4', 'admin', 'admin', '2017-02-24 13:45:29', '2017-02-24 14:22:42', '2', '01', '1', '0', '20', '2017-02-24 14:22:42');

-- ----------------------------
-- Table structure for tbl_sysmgr_aclroleresources
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_aclroleresources`;
CREATE TABLE `tbl_sysmgr_aclroleresources` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `resource_ids` text NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_aclroleresources
-- ----------------------------
INSERT INTO `tbl_sysmgr_aclroleresources` VALUES ('1', '1', '1000,1001,1003,1004,1005,1006,1007,1100,1101,1102,1103,1104,1105,1106,1200,1201,1300,1301,1302,1400,1401,1500,1600,1601,1602,1603,1604,1605,1700,1701,1702,1703,1704,1705,1800,1801,1802,1803,1804,1805,1900,1901,1902,1903,2000,2001,2100,2101,2102,2200,2201', '2017-02-21 19:39:19', '2017-02-21 21:27:34');
INSERT INTO `tbl_sysmgr_aclroleresources` VALUES ('2', '4', '1000,1001,1003,1004,1005,1006,1007,1100,1101,1102,1103,1104,1105,1106,1200,1201,1300,1301,1302,1400,1401,1500,1600,1601,1602,1603,1604,1605,1700,1701,1702,1703,1704,1705,1800,1801,1802,1803,1804,1805,1900,1901,1902,1903,2000,2001,2100,2101,2102,2200,2201', '2017-02-21 19:39:19', '2017-02-21 21:27:34');
INSERT INTO `tbl_sysmgr_aclroleresources` VALUES ('3', '2', '1000,1001', '2017-02-21 21:35:26', '2017-02-21 21:35:29');

-- ----------------------------
-- Table structure for tbl_sysmgr_acluser
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysmgr_acluser`;
CREATE TABLE `tbl_sysmgr_acluser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL,
  `role_ids` varchar(50) NOT NULL COMMENT '角色',
  `idcard` varchar(18) DEFAULT NULL COMMENT '身份证',
  `realName` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `peopleType` varchar(10) DEFAULT NULL COMMENT '人员类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `lockTime` datetime DEFAULT NULL COMMENT '锁定时间',
  `islock` varchar(1) DEFAULT NULL COMMENT '是否锁定Y N',
  `failCount` int(11) DEFAULT '0' COMMENT '失败次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysmgr_acluser
-- -----------------------密码123456-----
INSERT INTO `tbl_sysmgr_acluser` VALUES ('1', 'admin', '$2a$04$9OBSuBTlaata67Nc2BRDM.SAWqLHrQutcgW4kMrRi8rh/L2uID9Pi', null, '4', null, 'admin', '3', '2017-02-21 10:24:54', '2018-03-05 15:58:52', '13099999999', null, 'N', '0');
INSERT INTO `tbl_sysmgr_acluser` VALUES ('2', 'xiong', '$2a$04$9OBSuBTlaata67Nc2BRDM.SAWqLHrQutcgW4kMrRi8rh/L2uID9Pi', null, '4', null, 'admin', '3', '2017-02-21 10:24:54', '2018-03-02 10:11:45', '13099999999', null, 'N', '0');

-- ----------------------------
-- Table structure for tbl_sysset_systemconfig
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sysset_systemconfig`;
CREATE TABLE `tbl_sysset_systemconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `key_code` varchar(200) NOT NULL COMMENT '配置编码',
  `key_group` varchar(200) NOT NULL COMMENT '分组',
  `key_name` varchar(200) NOT NULL COMMENT '显示名字',
  `key_value` varchar(200) NOT NULL COMMENT '值',
  `comment` varchar(200) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_sysset_systemconfig
-- ----------------------------
INSERT INTO `tbl_sysset_systemconfig` VALUES ('1', '2016-09-22 00:00:00', '2016-09-22 00:00:00', '1', '1', '1', '1', '1');
INSERT INTO `tbl_sysset_systemconfig` VALUES ('2', '2016-09-23 17:35:18', '2016-09-23 17:35:22', '2', '2', '2', '2', '2');
