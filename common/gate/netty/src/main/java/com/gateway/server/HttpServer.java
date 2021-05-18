package com.gateway.server;

import com.base.utils.NamingThreadFactory;
import com.gateway.server.handler.HttpDispatcher;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("ALL")
@Slf4j
@Component("httpServer")
public class HttpServer extends AbstractNettyServer<ServerSocketChannel> {
    @Autowired
    private HttpDispatcher httpDispatcher;

    @Value("#{'${net.http.ports:80}'.split(',')}")
    private List<Integer> ports;

    @Override
    public List<Integer> ports() {
        return ports;
    }

    @Override
    public void initThread() {
        bootstrap = new ServerBootstrap();
        bossGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(bossNum, new NamingThreadFactory("hb")) : new NioEventLoopGroup(bossNum, new NamingThreadFactory("hb"));
        workerGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(workerNum, new NamingThreadFactory("hw")) : new NioEventLoopGroup(workerNum, new NamingThreadFactory("hw"));
    }

    @Override
    public void assignChannel() {
        bootstrap.channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
    }

    @Override
    public void assignOption() {
        //用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
        // 如果未设置或所设置的值小于1，Java将使用默认值50。
        // 服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
                //如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法；如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。
                .childOption(ChannelOption.TCP_NODELAY, false);
    }

    @Override
    public void addHandler(ChannelPipeline pipeline) {
        //HttpServerCodec 会将多个消息对象转变为单个 FullHttpRequest 或者 FullHttpResponse
        pipeline.addLast(new HttpServerCodec());
        //  HttpObjectAggregator 会将多个消息对象转变为单个 FullHttpRequest 或者 FullHttpResponse
        // body的最大长度 char长度
        pipeline.addLast(new HttpObjectAggregator(512));
        //纯Http请求
        pipeline.addLast(httpDispatcher);
    }
}
