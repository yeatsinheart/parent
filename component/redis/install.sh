# 下载位置
 cd ~
 PLACE="Downloads"
 mkdir -p $PLACE
 cd $PLACE
 VERSION="redis-6.2.2"
 rm -rf $VERSION".tar.gz"
 wget http://download.redis.io/releases/$VERSION".tar.gz"
 tar -zxf $VERSION".tar.gz"
 cd $VERSION
 make
sudo cp src/redis-server /usr/local/bin/
sudo cp src/redis-cli /usr/local/bin/
#自定义配置文件
#echo "daemonize yes">>redis.conf

#requirepass  这个就是配置redis访问密码的参数；
#比如 requirepass test123；
redis-server

#redis-cli ping
#PONG