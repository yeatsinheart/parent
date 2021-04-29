#安装前，我们可以检测系统是否自带安装 MySQL:
rpm -qa | grep mysql
#如果你系统有安装，那可以选择进行卸载:
#rpm -e mysql　　// 普通删除模式
# // 强力删除模式，如果使用上面命令删除时，提示有依赖的其它文件，则用该命令可以对其进行强力删除
rpm -e --nodeps mysql
#centos
wget http://repo.mysql.com/mysql-community-release-el7-5.noarch.rpm
rpm -ivh mysql-community-release-el7-5.noarch.rpm
yum update
yum install mysql-server

# 下载地址   https://dev.mysql.com/downloads/mysql/


生产中数据库使用的编码为utf8mb4, 校验规则为 utf8mb4_unicode_ci，对大小写不敏感

如果需要大小写敏感，需要将排序规则修改为utf8mb4_bin.
修改数据库配置后，不会对已经存在的表造成影响，如要生效需要修改特定列的排序规则。优先级大概是这样：列>表>数据库>服务器
CREATE TABLE `T2` (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  运行MySQL Server时，也可以使用—-character-set-server=charset_name及—-collation-server=collation_name参数指定服务器默认的字符集及Collation
SELECT DEFAULT_CHARACTER_SET_NAME 'charset', DEFAULT_COLLATION_NAME 'collation' FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = 'code';
## 修改成大小写敏感
ALTER DATABASE code DEFAULT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin';
ALTER TABLE logtest CONVERT TO CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin';
ALTER TABLE t CHANGE c c  CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin';
## 批量修改表大小写敏感
SELECT
	CONCAT(
		'ALTER TABLE `',
		TABLE_NAME,
		'` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;'
	)
FROM
	information_schema.`TABLES`
WHERE
	TABLE_SCHEMA = 'code';

utf8mb4编码后，主键id的长度设置255，太长，只能设置小于191的
utf8mb4_bin
将字符串每个字符用二进制数据编译存储，区分大小写，而且可以存二进制的内容。
	utf8mb4_unicode_ci
是基于 标准 的 Unicode 来排序和比较，能够在各种语言之间精确排序，Unicode排序规则为了能够处理特殊字符的情况，实现了略微复杂的排序算法。