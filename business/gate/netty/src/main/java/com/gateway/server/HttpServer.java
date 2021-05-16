package com.gateway.server;

import com.gateway.server.handler.HttpDispatcher;
import com.gateway.server.handler.HttpHandler;
import com.gateway.server.handler.TCPHandler;
import com.gateway.server.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.beans.factory.annotation.Autowired;

public class HttpServer extends AbstractNettyServer<ServerSocketChannel> {
    @Autowired
    HttpDispatcher httpDispatcher;
    @Autowired
    private HttpHandler http;
    @Autowired
    private WebSocketHandler webSocket;
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

    }

    @Override
    public void addHandler(ServerSocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        //HttpServerCodec 会将多个消息对象转变为单个 FullHttpRequest 或者 FullHttpResponse
        pipeline.addLast(new HttpServerCodec());
        //HttpObjectAggregator 会将多个消息对象转变为单个 FullHttpRequest 或者 FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(512 * 1024 * 1024));
        //websocket 和 http 握手请求分别处理
        pipeline.addLast(httpDispatcher);
        pipeline.addLast(http);
        pipeline.addLast(webSocket);
    }
}
