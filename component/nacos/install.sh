#最新发布Release版本地址
# https://github.com/alibaba/nacos/releases

# 下载位置
 cd ~
 PLACE="Downloads"
 mkdir -p $PLACE
 cd $PLACE
 VERSION="nacos-server-2.0.1.tar.gz"
 rm -rf $VERSION
 wget https://github.com/alibaba/nacos/releases/download/2.0.1/nacos-server-2.0.1.tar.gz
 tar -zxf $VERSION
 tar -zxf nacos-server-2.0.1.tar.gz
 #启动命令(standalone代表着单机模式运行，非集群模式):
 sh nacos/bin/startup.sh -m standalone
 cat nacos/logs/start.out
#默认只开启内网
