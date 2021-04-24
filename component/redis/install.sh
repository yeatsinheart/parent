# 下载位置
 cd ~
 PLACE="Downloads"
 mkdir -p $PLACE
 cd $PLACE
 VERSION="redis-6.2.2.tar.gz"
 rm -rf $VERSION
 wget https://download.redis.io/releases/$VERSION
 tar -zxf $VERSION

#默认只开启内网
