package com.gateway.server;

import com.base.utils.NamingThreadFactory;
import com.gateway.server.handler.UdpHandler;
import com.gateway.server.udp.UDPServerChannel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("ALL")
@Slf4j
@Component("udpServer")
public class UdpServer extends AbstractNettyServer<ServerSocketChannel> {
    @Autowired
    UdpHandler udp;
    @Value("#{'${net.udp.ports:7901}'.split(',')}")
    private List<Integer> ports;
//组播地址
//
//224.0.0.0～224.0.0.255        为预留的组播地址（永久组地址），地址224.0.0.0保留不做分配，其它地址供路由协议使用；
//224.0.1.0～224.0.1.255        是公用组播地址，可以用于Internet；欲使用需申请。
//224.0.2.0～238.255.255.255    为用户可用的组播地址（临时组地址），全网范围内有效；
//239.0.0.0～239.255.255.255    为本地管理组播地址，仅在特定的本地范围内有效。


    //单播(unicast): 是指封包在计算机网络的传输中，目的地址为单一目标的一种传输方式。它是现今网络应用最为广泛，通常所使用的网络协议或服务大多采用单播传输，例如一切基于TCP的协议。
    //组播(multicast): 也叫多播， 多点广播或群播。 指把信息同时传递给一组目的地址。它使用策略是最高效的，因为消息在每条网络链路上只需传递一次，而且只有在链路分叉的时候，消息才会被复制。
    //广播(broadcast):是指封包在计算机网络中传输时，目的地址为网络中所有设备的一种传输方式。实际上，这里所说的“所有设备”也是限定在一个范围之中，称为“广播域”。
    //任播(anycast):是一种网络寻址和路由的策略，使得资料可以根据路由拓朴来决定送到“最近”或“最好”的目的地。
    //
    //在Linux运行ifconfig, 如果网卡信息中包含UP BROADCAST RUNNING MULTICAST，则支持广播和组播。


    // 1) 首先IP协议规定多点广播地址的范围是224.0.0.0 ~ 239.255.255.255，协议软件底层设定好的，如果超出这个返回就会抛出异常；
    //
    //    2) 其次是参与组播的所有主机的socket必须是MulticastSocket类的对象，它是DatagramSocket的子类，因此组播socket也是UDP协议的一种！！


    //    3) 也就是说单点（一对一）UDP通信就用DatagramSocket，组播群体通信就得使用MulticastSocket，
    //    如果程序中同时需要这两种通信方式，那么就得同时用这两种socket，但这两种socket不能共享一个端口，必须分别指定，否则会抛出异常；

    //！！这是因为单点和组播无法在同一个端口上监听（receive）！！
    //
    //    4) MulticastSocket的构造器和DatagramSocket以及其它所有Socket大同小异，无非都是绑定本机的IP和端口罢了；
    //
    //    5) 要想加入组播就必须先让自己的socket“加入”到目标广播IP中，直接调用MulticastSocket对象的joinGroup方法加入到该IP地址中即可（该方法的参数就是广播IP地址）；
    //
    //    6) 接下来的发送和接受信息就和DatagramSocket一模一样，都是定义一下DatagramPacket，其余完全一样，只不过发送的消息会被广播到组内所有机器，接受的消息时其它机器的广播消息罢了；
    //
    //！！发送数据报时的目的地端口和MulticastSocket绑定的端口一样！！其实在IP协议底层，MulticastSocket（成员socket）既是客户端也是服务器端（广播IP）；

    @Override
    public List<Integer> ports() {
        return ports;
    }

    @Override
    public void initThread() {
        bootstrap = new ServerBootstrap();
        bossGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(bossNum, new NamingThreadFactory("ub")) : new NioEventLoopGroup(bossNum, new NamingThreadFactory("ub"));
        workerGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(workerNum, new NamingThreadFactory("uw")) : new DefaultEventLoopGroup(workerNum, new NamingThreadFactory("uw"));
    }

    @Override
    public void assignChannel() {
        bootstrap.channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : UDPServerChannel.class);
    }

    @Override
    public void assignOption() {
        //bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
        //.option(ChannelOption.SO_BROADCAST,true)
        //      .childOption(ChannelOption.TCP_NODELAY, true);
    }

    @Override
    public void addHandler(ChannelPipeline pipeline) {
        //tcp处理
        pipeline.addLast(udp);
    }
}
