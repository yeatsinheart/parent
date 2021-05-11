DROP
DATABASE IF EXISTS `code`;
CREATE
DATABASE  `code`;
use
`code`;
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
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus`
(
    `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK
TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `global_admin`
--

DROP TABLE IF EXISTS `global_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_admin`
(
    `id`      int                                                    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
    `pwd`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `status`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_admin`
--

LOCK
TABLES `global_admin` WRITE;
/*!40000 ALTER TABLE `global_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_admin` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `global_api`
--

DROP TABLE IF EXISTS `global_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_api`
(
    `id`       int                                                    NOT NULL COMMENT '主键',
    `title`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
    `module`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '模块',
    `service`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '服务类',
    `method`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '方法',
    `descript` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '接口说明',
    `logined`  tinyint                                                NOT NULL DEFAULT '0' COMMENT '是否登陆',
    `authed`   tinyint                                                NOT NULL DEFAULT '0' COMMENT '是否权限',
    `request`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '请求参数样例',
    `response` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '响应参数样例',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='接口';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_api`
--

LOCK
TABLES `global_api` WRITE;
/*!40000 ALTER TABLE `global_api` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_api` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `global_resource`
--

DROP TABLE IF EXISTS `global_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_resource`
(
    `id`        int                                                    DEFAULT NULL,
    `type`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型：菜单/功能',
    `title`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '显示名的语言KEY',
    `url`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '对应界面URL',
    `api_id`    int                                                    DEFAULT NULL COMMENT '对应接口功能',
    `status`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
    `parent_id` int                                                    DEFAULT NULL COMMENT '上级资源节点',
    `sequence`  int                                                    DEFAULT NULL COMMENT '同级别顺序，越小越靠前'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_resource`
--

LOCK
TABLES `global_resource` WRITE;
/*!40000 ALTER TABLE `global_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_resource` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `global_table_id`
--

DROP TABLE IF EXISTS `global_table_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_table_id`
(
    `id`     int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `gen_id` bigint                                                 DEFAULT NULL COMMENT '主键ID',
    `table`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表名',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_table_id`
--

LOCK
TABLES `global_table_id` WRITE;
/*!40000 ALTER TABLE `global_table_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_table_id` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `op_log`
--

DROP TABLE IF EXISTS `op_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `op_log`
(
    `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `op_log`
--

LOCK
TABLES `op_log` WRITE;
/*!40000 ALTER TABLE `op_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `op_log` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order`
(
    `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK
TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant`
(
    `id`       int NOT NULL AUTO_INCREMENT COMMENT '主键/租户ID',
    `type`     int                                                    DEFAULT NULL COMMENT '类型：主站/子站',
    `relation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '族谱1->2->3->4',
    `status`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '可用，维护，暂停，关站时候注意把族谱后的站点分析出来，给租户考虑是否关站',
    `key`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '加密key',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK
TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_admin`
--

DROP TABLE IF EXISTS `tenant_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_admin`
(
    `id`        int                                                    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `account`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
    `pwd`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `status`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_admin`
--

LOCK
TABLES `tenant_admin` WRITE;
/*!40000 ALTER TABLE `tenant_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_admin` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_admin_role`
--

DROP TABLE IF EXISTS `tenant_admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_admin_role`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `admin_id` int NOT NULL COMMENT '管理员ID',
    `role_id`  int NOT NULL COMMENT '租户资源ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_admin_role`
--

LOCK
TABLES `tenant_admin_role` WRITE;
/*!40000 ALTER TABLE `tenant_admin_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_admin_role` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_currency`
--

DROP TABLE IF EXISTS `tenant_currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_currency`
(
    `id`        int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    `currency`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '币种',
    `active`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否默认币种',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_currency`
--

LOCK
TABLES `tenant_currency` WRITE;
/*!40000 ALTER TABLE `tenant_currency` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_currency` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_domain`
--

DROP TABLE IF EXISTS `tenant_domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_domain`
(
    `id`        int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    `domain`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '域名',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_domain`
--

LOCK
TABLES `tenant_domain` WRITE;
/*!40000 ALTER TABLE `tenant_domain` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_domain` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_language`
--

DROP TABLE IF EXISTS `tenant_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_language`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    `language`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户语言',
    `active`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否默认语言',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_language`
--

LOCK
TABLES `tenant_language` WRITE;
/*!40000 ALTER TABLE `tenant_language` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_language` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_resource`
--

DROP TABLE IF EXISTS `tenant_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_resource`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `tenant_id`   int DEFAULT NULL COMMENT '租户ID',
    `resource_id` int DEFAULT NULL COMMENT '全局资源ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_resource`
--

LOCK
TABLES `tenant_resource` WRITE;
/*!40000 ALTER TABLE `tenant_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_resource` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_role`
--

DROP TABLE IF EXISTS `tenant_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_role`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_role`
--

LOCK
TABLES `tenant_role` WRITE;
/*!40000 ALTER TABLE `tenant_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_role` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_role_resource`
--

DROP TABLE IF EXISTS `tenant_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_role_resource`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `role_id`     int DEFAULT NULL COMMENT '角色ID',
    `resource_id` int DEFAULT NULL COMMENT '资源ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_role_resource`
--

LOCK
TABLES `tenant_role_resource` WRITE;
/*!40000 ALTER TABLE `tenant_role_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_role_resource` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_template`
--

DROP TABLE IF EXISTS `tenant_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_template`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    `template`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '站点样式',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_template`
--

LOCK
TABLES `tenant_template` WRITE;
/*!40000 ALTER TABLE `tenant_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_template` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tenant_wallet`
--

DROP TABLE IF EXISTS `tenant_wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_wallet`
(
    `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_wallet`
--

LOCK
TABLES `tenant_wallet` WRITE;
/*!40000 ALTER TABLE `tenant_wallet` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_wallet` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`        int                                                    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `account`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
    `pwd`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `nick`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK
TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user_0`
--

DROP TABLE IF EXISTS `user_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_0`
(
    `id`        int                                                    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `account`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
    `pwd`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `nick`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_0`
--

LOCK
TABLES `user_0` WRITE;
/*!40000 ALTER TABLE `user_0` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_0` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user_1`
--

DROP TABLE IF EXISTS `user_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_1`
(
    `id`        int                                                    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `account`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
    `pwd`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `nick`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_1`
--

LOCK
TABLES `user_1` WRITE;
/*!40000 ALTER TABLE `user_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_1` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user_3`
--

DROP TABLE IF EXISTS `user_3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_3`
(
    `id`        int                                                    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `account`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
    `pwd`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `nick`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
    `tenant_id` int                                                    DEFAULT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_3`
--

LOCK
TABLES `user_3` WRITE;
/*!40000 ALTER TABLE `user_3` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_3` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user_wallet`
--

DROP TABLE IF EXISTS `user_wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_wallet`
(
    `id`       int NOT NULL,
    `user_id`  int                                                    DEFAULT NULL,
    `currency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    `balance`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账户余额',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wallet`
--

LOCK
TABLES `user_wallet` WRITE;
/*!40000 ALTER TABLE `user_wallet` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_wallet` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user_wallet_log`
--

DROP TABLE IF EXISTS `user_wallet_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_wallet_log`
(
    `id`        int NOT NULL COMMENT '帐变流水号',
    `wallet_id` int                              DEFAULT NULL COMMENT '钱包ID',
    `op`        varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作名',
    `before`    bigint                           DEFAULT NULL COMMENT '帐变前余额',
    `after`     bigint                           DEFAULT NULL COMMENT '帐变后余额',
    `amount`    bigint                           DEFAULT NULL COMMENT '帐变金额',
    `voucher`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '帐变凭证',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wallet_log`
--

LOCK
TABLES `user_wallet_log` WRITE;
/*!40000 ALTER TABLE `user_wallet_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_wallet_log` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-11 20:11:37
