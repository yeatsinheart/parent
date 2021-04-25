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
