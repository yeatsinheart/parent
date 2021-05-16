package com.gateway.server;

import com.common.utils.NamingThreadFactory;
import com.gateway.server.handler.TCPHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("ALL")
@Slf4j
@Component("tcpServer")
public class TcpServer extends AbstractNettyServer<ServerSocketChannel> {


    @Autowired
    private TCPHandler tcp;
    @Value("#{'${net.tcp.ports:8901}'.split(',')}")
    private List<Integer> ports;

    @Override
    public List<Integer> ports() {
        return ports;
    }

    @Override
    public void initThread() {
        bootstrap = new ServerBootstrap();
        bossGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(bossNum, new NamingThreadFactory("tb")) : new NioEventLoopGroup(bossNum, new NamingThreadFactory("tb"));
        workerGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(workerNum, new NamingThreadFactory("tw")) : new NioEventLoopGroup(workerNum, new NamingThreadFactory("tw"));
    }

    @Override
    public void assignChannel() {
        bootstrap.channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
    }

    @Override
    public void assignOption() {
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);
    }

    @Override
    public void addHandler(ChannelPipeline pipeline) {
        //tcp处理
        pipeline.addLast(tcp);
    }
}
