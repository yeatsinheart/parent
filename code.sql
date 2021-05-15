DROP DATABASE IF EXISTS `code`;
CREATE DATABASE  `code`;
use  `code`;
-- MySQL dump 10.13  Distrib 8.0.23, for osx10.16 (x86_64)
--
-- Host: localhost    Database: code
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `code`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `code` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `code`;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_admin`
--

DROP TABLE IF EXISTS `global_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
  `salt` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '盐值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_admin`
--

LOCK TABLES `global_admin` WRITE;
/*!40000 ALTER TABLE `global_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_api`
--

DROP TABLE IF EXISTS `global_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_api` (
  `id` int NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  `module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模块',
  `service` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '服务类',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '方法',
  `descript` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '接口说明',
  `logined` tinyint NOT NULL DEFAULT '0' COMMENT '是否登陆',
  `authed` tinyint NOT NULL DEFAULT '0' COMMENT '是否权限',
  `request` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '请求参数样例',
  `response` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '响应参数样例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='接口';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_api`
--

LOCK TABLES `global_api` WRITE;
/*!40000 ALTER TABLE `global_api` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_resource`
--

DROP TABLE IF EXISTS `global_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型：0目录/1页面/2功能',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '显示名的语言KEY',
  `name_for_it` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '方便开发查看的，比如针对定制化的一些页面菜单',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '对应界面URL',
  `api_id` int DEFAULT NULL COMMENT '对应接口功能',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态：某些功能禁止使用',
  `parent_id` int DEFAULT NULL COMMENT '上级资源节点',
  `sequence` int DEFAULT NULL COMMENT '同级别顺序，越小越靠前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='全局资源表，涉及菜单，页面中需要权限限制的具体的功能，需要同步到nacos，做全局资源配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_resource`
--

LOCK TABLES `global_resource` WRITE;
/*!40000 ALTER TABLE `global_resource` DISABLE KEYS */;
INSERT INTO `global_resource` VALUES (1,'0','system','系统',NULL,NULL,NULL,0,NULL),(2,'1','admin_manage','人员管理','/admin',NULL,NULL,1,NULL),(3,'1','role_manage','权限管理','/role',NULL,NULL,1,NULL),(4,'1','menu_manage','菜单管理','/menu',NULL,NULL,1,NULL),(5,'1','info_manage','基础信息',NULL,NULL,NULL,1,NULL),(6,'0','tenant','租户',NULL,NULL,NULL,0,NULL),(7,'1','tenant_manage','租户管理',NULL,NULL,NULL,6,NULL),(8,'1','tenant_resource','资源管理',NULL,NULL,NULL,6,NULL),(9,'1','app_version','软件版本',NULL,NULL,NULL,6,NULL),(10,'1','domain_manage','域名管理',NULL,NULL,NULL,6,NULL),(11,'0','agent','代理管理',NULL,NULL,NULL,0,NULL),(12,'1','agent_manage','代理管理，默认有平台',NULL,NULL,NULL,11,NULL),(13,'0','user','会员管理',NULL,NULL,NULL,11,NULL),(14,'1','user_manage','会员管理',NULL,NULL,NULL,11,NULL),(15,'1','user_test','带客账号，测试账号，一切按照正常流程走',NULL,NULL,NULL,11,NULL),(16,'1','user_balance_log','帐变管理',NULL,NULL,NULL,11,NULL),(17,'1','user_limit_log','打码量记录',NULL,NULL,NULL,11,NULL),(18,'1','user_level','会员等级',NULL,NULL,NULL,11,NULL),(19,'1','user_black','黑名单管理',NULL,NULL,NULL,11,NULL),(20,'1','user_op','操作记录',NULL,NULL,NULL,11,NULL),(21,'0','money','出入款',NULL,NULL,NULL,0,NULL),(22,'1','money_hand','人工上分',NULL,NULL,NULL,21,NULL),(23,'1','money_user_pay_way','入款渠道',NULL,NULL,NULL,21,NULL),(24,'1','money_withdraw','出款管理',NULL,NULL,NULL,21,NULL),(25,'1','money_recharge','入款管理',NULL,NULL,NULL,21,NULL),(26,'1','money_log','出入款记录',NULL,NULL,NULL,21,NULL),(27,'0','money_third','三方通道',NULL,NULL,NULL,21,NULL),(28,'1','money_in_channel','充值渠道',NULL,NULL,NULL,27,NULL),(29,'1','money_out_channel','代付渠道',NULL,NULL,NULL,27,NULL),(30,'1','pay','汇率管理',NULL,NULL,NULL,21,NULL),(31,'0','cost','成本营销',NULL,NULL,NULL,0,NULL),(32,'1','coast_vip','VIP礼金',NULL,NULL,NULL,31,NULL),(33,'1','coast_first_recharge','首充',NULL,NULL,NULL,31,NULL),(34,'1','','活动',NULL,NULL,NULL,31,NULL),(35,'1','pay','返水',NULL,NULL,NULL,31,NULL),(36,'1','pay','红包雨',NULL,NULL,NULL,31,NULL),(37,'1','pay','幸运轮盘',NULL,NULL,NULL,31,NULL),(38,'1','pay','全民代理',NULL,NULL,NULL,31,NULL),(39,'1','pay','日工资',NULL,NULL,NULL,31,NULL),(40,'1','pay','佣金',NULL,NULL,NULL,31,NULL),(41,'1','pay','分红',NULL,NULL,NULL,31,NULL),(42,'1','pay','占成',NULL,NULL,NULL,31,NULL),(43,'1','pay','全民代理',NULL,NULL,NULL,31,NULL),(44,'1','pay','发放管理',NULL,NULL,NULL,31,NULL),(45,'0','game','游戏平台',NULL,NULL,NULL,0,NULL),(46,'1','game_manage','游戏管理',NULL,NULL,NULL,45,NULL),(47,'1','game_categery','大类管理，关联游戏，设定是否主营',NULL,NULL,NULL,45,NULL),(48,'1','game_balance','三方余额',NULL,NULL,NULL,45,NULL),(49,'1','game_','派奖管理',NULL,NULL,NULL,45,NULL),(50,'1','game_open','开号管理',NULL,NULL,NULL,45,NULL),(51,'1','game_descript','当期投注',NULL,NULL,NULL,45,NULL),(52,'1','game_log','游戏记录',NULL,NULL,NULL,45,NULL),(53,'1','user_tax','抽水管理',NULL,NULL,NULL,45,NULL),(54,'1','game_hot','热门游戏',NULL,NULL,NULL,45,NULL),(55,'0','report','统计报表',NULL,NULL,NULL,0,NULL),(56,'1','pay','盈利报表',NULL,NULL,NULL,55,NULL),(57,'1','pay','充值报表',NULL,NULL,NULL,55,NULL),(58,'1','pay','投注报表',NULL,NULL,NULL,55,NULL),(59,'1','pay','提现报表',NULL,NULL,NULL,55,NULL),(60,'1','pay','成本报表',NULL,NULL,NULL,55,NULL),(61,'1','pay','活跃报表',NULL,NULL,NULL,55,NULL),(62,'1','pay','新增报表',NULL,NULL,NULL,55,NULL),(63,'0','chat','聊天通讯',NULL,NULL,NULL,0,NULL),(64,'1','chat','聊天设置，会员敏感词过滤',NULL,NULL,NULL,63,NULL),(65,'1','chat','禁言设置',NULL,NULL,NULL,63,NULL),(66,'1','chat_group','小组管理，公告，礼物',NULL,NULL,NULL,63,NULL),(67,'1','customer','客服',NULL,NULL,NULL,63,NULL),(68,'1','chat_user','私聊',NULL,NULL,NULL,63,NULL),(69,'1','chat_user','红包，限制test币种',NULL,NULL,NULL,63,NULL),(70,'0','tenant','平台设置',NULL,NULL,NULL,0,NULL),(71,'1','tenant_info','基础信息，币种，语言，logo+平台名字（多语言）',NULL,NULL,NULL,70,NULL),(72,'1','tenant_domain','域名管理，绑定代理，不设置默认为游客，对应Reffer，语言币种','/menu',NULL,NULL,70,NULL),(73,'1','tenant_signin','注册管理，注册需要填写信息配置','/menu',NULL,NULL,70,NULL),(74,'1','tenant_ip_area','地区管理，ip拦截','/menu',NULL,NULL,70,NULL),(75,'1','tenant_ip_black','IP黑名单','/menu',NULL,NULL,70,NULL),(76,'1','tenant_ip','流量',NULL,NULL,NULL,70,NULL),(77,'1','tenant_message','公告设置',NULL,NULL,NULL,70,NULL),(78,'1','tenant_alert','弹窗设置',NULL,NULL,NULL,70,NULL),(79,'1','tenant_welcome','启动屏',NULL,NULL,NULL,70,NULL),(80,'1','tenant_admin','人员管理','/admin',NULL,NULL,70,NULL),(81,'1','tenant_role','权限管理，关联资源，设置敏感字段','/role',NULL,NULL,70,NULL),(82,'1','tenant_op','操作记录','/menu',NULL,NULL,70,NULL),(83,'0','open','开站系统',NULL,NULL,NULL,0,NULL),(84,'1','tenant_manage','租户管理，维护，终止，运营中',NULL,NULL,NULL,83,NULL),(85,'1','app_version','软件版本',NULL,NULL,NULL,83,NULL),(86,'1','app_version','PC模版',NULL,NULL,NULL,83,NULL),(87,'1','app_version','H5模版',NULL,NULL,NULL,83,NULL),(88,'1','game_manage','游戏管理',NULL,NULL,NULL,83,NULL),(89,'1','resource','资源管理，设置关联接口，限制字段，字段值，页面也有默认调用接口',NULL,NULL,NULL,83,NULL);
/*!40000 ALTER TABLE `global_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_table_id`
--

DROP TABLE IF EXISTS `global_table_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_table_id` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gen_id` bigint DEFAULT NULL COMMENT '主键ID',
  `table` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_table_id`
--

LOCK TABLES `global_table_id` WRITE;
/*!40000 ALTER TABLE `global_table_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_table_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `op_log`
--

DROP TABLE IF EXISTS `op_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `op_log` (
  `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `op_log`
--

LOCK TABLES `op_log` WRITE;
/*!40000 ALTER TABLE `op_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `op_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键/租户ID',
  `type` int DEFAULT NULL COMMENT '类型：主站/子站',
  `relation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '族谱1->2->3->4',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '可用，维护，暂停，关站时候注意把族谱后的站点分析出来，给租户考虑是否关站',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '加密key',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_admin`
--

DROP TABLE IF EXISTS `tenant_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_admin`
--

LOCK TABLES `tenant_admin` WRITE;
/*!40000 ALTER TABLE `tenant_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_admin_role`
--

DROP TABLE IF EXISTS `tenant_admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_admin_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NOT NULL COMMENT '管理员ID',
  `role_id` int NOT NULL COMMENT '租户资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_admin_role`
--

LOCK TABLES `tenant_admin_role` WRITE;
/*!40000 ALTER TABLE `tenant_admin_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_currency`
--

DROP TABLE IF EXISTS `tenant_currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_currency` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  `currency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '币种',
  `active` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否默认币种',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_currency`
--

LOCK TABLES `tenant_currency` WRITE;
/*!40000 ALTER TABLE `tenant_currency` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_domain`
--

DROP TABLE IF EXISTS `tenant_domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_domain` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '域名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_domain`
--

LOCK TABLES `tenant_domain` WRITE;
/*!40000 ALTER TABLE `tenant_domain` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_domain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_language`
--

DROP TABLE IF EXISTS `tenant_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_language` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户语言',
  `active` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否默认语言',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_language`
--

LOCK TABLES `tenant_language` WRITE;
/*!40000 ALTER TABLE `tenant_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_resource`
--

DROP TABLE IF EXISTS `tenant_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  `resource_id` int DEFAULT NULL COMMENT '全局资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_resource`
--

LOCK TABLES `tenant_resource` WRITE;
/*!40000 ALTER TABLE `tenant_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_role`
--

DROP TABLE IF EXISTS `tenant_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_role`
--

LOCK TABLES `tenant_role` WRITE;
/*!40000 ALTER TABLE `tenant_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_role_resource`
--

DROP TABLE IF EXISTS `tenant_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_role_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `resource_id` int DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_role_resource`
--

LOCK TABLES `tenant_role_resource` WRITE;
/*!40000 ALTER TABLE `tenant_role_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_template`
--

DROP TABLE IF EXISTS `tenant_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_template` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  `template` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '站点样式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_template`
--

LOCK TABLES `tenant_template` WRITE;
/*!40000 ALTER TABLE `tenant_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_wallet`
--

DROP TABLE IF EXISTS `tenant_wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_wallet` (
  `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_wallet`
--

LOCK TABLES `tenant_wallet` WRITE;
/*!40000 ALTER TABLE `tenant_wallet` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `nick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_0`
--

DROP TABLE IF EXISTS `user_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_0` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `nick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_0`
--

LOCK TABLES `user_0` WRITE;
/*!40000 ALTER TABLE `user_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_1`
--

DROP TABLE IF EXISTS `user_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_1` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `nick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_1`
--

LOCK TABLES `user_1` WRITE;
/*!40000 ALTER TABLE `user_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_3`
--

DROP TABLE IF EXISTS `user_3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_3` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `nick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_3`
--

LOCK TABLES `user_3` WRITE;
/*!40000 ALTER TABLE `user_3` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_wallet`
--

DROP TABLE IF EXISTS `user_wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_wallet` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `currency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `balance` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wallet`
--

LOCK TABLES `user_wallet` WRITE;
/*!40000 ALTER TABLE `user_wallet` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_wallet_log`
--

DROP TABLE IF EXISTS `user_wallet_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_wallet_log` (
  `id` int NOT NULL COMMENT '帐变流水号',
  `wallet_id` int DEFAULT NULL COMMENT '钱包ID',
  `op` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作名',
  `before` bigint DEFAULT NULL COMMENT '帐变前余额',
  `after` bigint DEFAULT NULL COMMENT '帐变后余额',
  `amount` bigint DEFAULT NULL COMMENT '帐变金额',
  `voucher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '帐变凭证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wallet_log`
--

LOCK TABLES `user_wallet_log` WRITE;
/*!40000 ALTER TABLE `user_wallet_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_wallet_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-15 10:57:17
