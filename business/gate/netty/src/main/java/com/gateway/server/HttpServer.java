package com.gateway.server;

import com.gateway.server.handler.HttpDispatcher;
import com.gateway.server.handler.HttpHandler;
import com.gateway.server.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
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
    @Autowired
    private HttpHandler http;
    @Autowired
    private WebSocketHandler webSocket;
    @Value("#{'${net.http.ports:80}'.split(',')}")
    private List<Integer> ports;

    @Override
    public List<Integer> ports() {
        return ports;
    }

    @Override
    public void initThread() {
        bootstrap = new ServerBootstrap();
        bossGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(bossNum) : new NioEventLoopGroup(bossNum);
        workerGroup = Epoll.isAvailable() ? new EpollEventLoopGroup(workerNum) : new NioEventLoopGroup(workerNum);
    }

    @Override
    public void assignChannel() {
        bootstrap.channel(Epoll.isAvailable() ? EpollServerSocketChannel.class : ServerSocketChannel.class);
    }

    @Override
    public void assignOption() {
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);
    }

    @Override
    public void addHandler(ChannelPipeline pipeline) {
        //HttpServerCodec 会将多个消息对象转变为单个 FullHttpRequest 或者 FullHttpResponse
        pipeline.addLast(new HttpServerCodec());
        //HttpObjectAggregator 会将多个消息对象转变为单个 FullHttpRequest 或者 FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(512 * 1024 * 1024));
        //websocket 和 http 握手请求分别处理
        pipeline.addLast(httpDispatcher);
        //纯Http请求
        pipeline.addLast(http);
        //纯websocket通信
        pipeline.addLast(webSocket);
    }
}
