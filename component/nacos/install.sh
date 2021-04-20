最新发布Release版本地址
 https://github.com/alibaba/nacos/releases

 下载位置
 mkdir -p ~/Downloads/testnacos/
 cd ~/Downloads/testnacos/

 wget https://github.com/alibaba/nacos/releases/download/2.0.0-bugfix/nacos-server-2.0.0.tar.gz
 tar -zxf nacos-server-2.0.0.tar.gz

 启动命令(standalone代表着单机模式运行，非集群模式):
 sh ~/Downloads/nacos/bin/startup.sh -m standalone

默认只开启内网
