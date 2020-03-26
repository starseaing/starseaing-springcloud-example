CREATE TABLE `fm_form` (
  `ID` varchar(36) NOT NULL COMMENT '主键',
  `FORM_ID` varchar(100) NOT NULL COMMENT '表单ID',
  `FORM_NAME` varchar(36) NOT NULL COMMENT '表单名称',
  `FORM_CONTENT` longtext DEFAULT NULL COMMENT '表单的文本内容',
  `CREATOR_ID` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `MODIFIER_ID` varchar(50) DEFAULT NULL COMMENT '最后修改人ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '行记录创建时间',
  `MODIFIED_TIME` datetime DEFAULT NULL COMMENT '行记录修改时间',
  PRIMARY KEY (`id`)
) COMMENT '表单信息表';



CREATE TABLE `fm_form_data` (
  `ID` varchar(36) NOT NULL COMMENT '主键',
  `BUSINESS_ID` varchar(100) NOT NULL COMMENT '业务ID',
  `FORM_ID` varchar(100) NOT NULL COMMENT '表单ID',
  `DATA_ID` varchar(36) NOT NULL COMMENT '表单数据ID',
  `JSON_DATA` longtext DEFAULT NULL COMMENT '表单的json字符串数据',
  `CREATOR_ID` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `MODIFIER_ID` varchar(50) DEFAULT NULL COMMENT '最后修改人ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '行记录创建时间',
  `MODIFIED_TIME` datetime DEFAULT NULL COMMENT '行记录修改时间',
  PRIMARY KEY (`ID`)
)  COMMENT '表单数据表';

