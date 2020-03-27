-- ----------------------------
-- Table structure for ACT_ID_GROUP
-- ----------------------------
CREATE TABLE `ACT_ID_GROUP` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
);

-- ----------------------------
-- Records of ACT_ID_GROUP
-- ----------------------------
INSERT INTO `ACT_ID_GROUP` VALUES ('admin', '1', 'Admin', 'security-role');
INSERT INTO `ACT_ID_GROUP` VALUES ('dept_leader', '2', '部门领导组', 'assignment');
INSERT INTO `ACT_ID_GROUP` VALUES ('dev', '1', '研发组', 'assignment');
INSERT INTO `ACT_ID_GROUP` VALUES ('engineering', '1', 'Engineering', 'assignment');
INSERT INTO `ACT_ID_GROUP` VALUES ('management', '1', 'Management', 'assignment');
INSERT INTO `ACT_ID_GROUP` VALUES ('marketing', '1', 'Marketing', 'assignment');
INSERT INTO `ACT_ID_GROUP` VALUES ('prod', '1', '产品组', 'assignment');
INSERT INTO `ACT_ID_GROUP` VALUES ('sales', '1', 'Sales', 'assignment');
INSERT INTO `ACT_ID_GROUP` VALUES ('test', '1', '测试组', 'assignment');
INSERT INTO `ACT_ID_GROUP` VALUES ('user', '1', 'User', 'security-role');

-- ----------------------------
-- Table structure for ACT_ID_USER
-- ----------------------------
CREATE TABLE `ACT_ID_USER` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ;

-- ----------------------------
-- Records of ACT_ID_USER
-- ----------------------------
INSERT INTO `ACT_ID_USER` VALUES ('fozzie', '2', 'Fozzie', 'Bear', 'fozzie@activiti.org', 'fozzie', '22');
INSERT INTO `ACT_ID_USER` VALUES ('gonzo', '2', 'Gonzo', 'The Great', 'gonzo@activiti.org', 'gonzo', '18');
INSERT INTO `ACT_ID_USER` VALUES ('kermit', '2', 'Kermit', 'The Frog', 'kermit@activiti.org', 'kermit', '7');
INSERT INTO `ACT_ID_USER` VALUES ('lisi', '2', 'si', 'li', 'lisi@mail.com', 'lisii', null);
INSERT INTO `ACT_ID_USER` VALUES ('lisi02', '1', 'si02', 'li', 'lisi02@mail.com', 'lisi02', null);
INSERT INTO `ACT_ID_USER` VALUES ('wangjingli', '1', '经理', '王', 'wangjingli@mail.com', '123456', null);
INSERT INTO `ACT_ID_USER` VALUES ('wangwu', '1', 'wu', 'wang', 'wangwu@mail.com', 'wangwu', null);
INSERT INTO `ACT_ID_USER` VALUES ('wangwu02', '1', 'wu02', 'wang', 'wangwu02@mailcom', 'wangwu02', null);
INSERT INTO `ACT_ID_USER` VALUES ('zhangsan', '1', 'san', 'zhang', 'zhangsan@mail.com', 'zhangsan', null);
INSERT INTO `ACT_ID_USER` VALUES ('zhangsan02', '1', 'san02', 'zhang', 'zhangsan02@mail.com', 'zhangsan02', null);




-- ----------------------------
-- Table structure for ACT_ID_INFO
-- ----------------------------
CREATE TABLE `ACT_ID_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
);

-- ----------------------------
-- Records of ACT_ID_INFO
-- ----------------------------
INSERT INTO `ACT_ID_INFO` VALUES ('10', '1', 'kermit', 'userinfo', 'location', 'Hollywoord', null, null);
INSERT INTO `ACT_ID_INFO` VALUES ('11', '1', 'kermit', 'userinfo', 'phone', '+123456789', null, null);
INSERT INTO `ACT_ID_INFO` VALUES ('12', '1', 'kermit', 'userinfo', 'twitterName', 'alfresco', null, null);
INSERT INTO `ACT_ID_INFO` VALUES ('13', '1', 'kermit', 'userinfo', 'skype', 'activiti_kermit_frog', null, null);
INSERT INTO `ACT_ID_INFO` VALUES ('8', '1', 'kermit', 'userinfo', 'birthDate', '10-10-1955', null, null);
INSERT INTO `ACT_ID_INFO` VALUES ('9', '1', 'kermit', 'userinfo', 'jobTitle', 'Muppet', null, null);

-- ----------------------------
-- Table structure for ACT_ID_MEMBERSHIP
-- ----------------------------
CREATE TABLE `ACT_ID_MEMBERSHIP` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `ACT_ID_GROUP` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `ACT_ID_USER` (`ID_`)
);

-- ----------------------------
-- Records of ACT_ID_MEMBERSHIP
-- ----------------------------
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('kermit', 'admin');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('wangjingli', 'dept_leader');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('zhangsan', 'dev');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('zhangsan02', 'dev');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('fozzie', 'engineering');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('kermit', 'engineering');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('gonzo', 'management');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('kermit', 'management');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('fozzie', 'marketing');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('gonzo', 'marketing');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('kermit', 'marketing');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('wangwu', 'prod');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('wangwu02', 'prod');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('gonzo', 'sales');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('kermit', 'sales');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('lisi', 'test');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('lisi02', 'test');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('fozzie', 'user');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('gonzo', 'user');
INSERT INTO `ACT_ID_MEMBERSHIP` VALUES ('kermit', 'user');

