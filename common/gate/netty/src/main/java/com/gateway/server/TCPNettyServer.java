package com.gateway.server;

import com.base.utils.NamingThreadFactory;
import com.gateway.server.handler.HttpDispatcher;
import com.gateway.server.handler.HttpHandler;
import com.gateway.server.handler.TCPHandler;
import com.gateway.server.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Component("tcpServer")
public class TCPNettyServer {
    public static int TCP_FRAME_FIXED_HEADER_LENGTH = 4;     // 4 bytes
    public static int TCP_FRAME_MAX_BODY_LENGTH = 6 * 1024; // 6K bytes
    @Value("${session.online.time}")
    public int SESION_RECYCLER_EXPIRE = 20;//10;
    public ExecutorService serverStartor;
    public ServerBootstrap bootstrap;
    //Runtime.getRuntime().availableProcessors()
    public EventLoopGroup bossGroup;
    //Runtime.getRuntime().availableProcessors() * 2,
    public EventLoopGroup workerGroup;
    @Value("${net.tcp.ports}")
    public int port;

    @Autowired
    HttpDispatcher httpDispatcher;
    @Autowired
    private TCPHandler tcp;
    @Autowired
    private HttpHandler http;
    @Autowired
    private WebSocketHandler webSocket;

    // netty通过Reactor模型基于多路复用器接收并处理用户请求（能讲就多讲一点），
    // 内部实现了两个线程池，boss线程池和work线程池，
    // 其中boss线程池的线程负责处理请求的accept事件，当接收到accept事件的请求时，把对应的socket封装到一个NioSocketChannel中，
    // 并交给work线程池，其中work线程池负责请求的read和write事件
    @PostConstruct
    public void start() {
        bootstrap = new ServerBootstrap();
        serverStartor = Executors.newSingleThreadExecutor(new NamingThreadFactory("p-" + port));
        serverStartor.execute(() -> {
            int coreNum = Runtime.getRuntime().availableProcessors();
            bossGroup = new NioEventLoopGroup(new NamingThreadFactory(port + "-p"));
            workerGroup = new NioEventLoopGroup(new NamingThreadFactory(port + "-c"));
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(initChildChannelHandler());
            bootstrap.option(ChannelOption.SO_BACKLOG, 5000)
                    .option(ChannelOption.SO_REUSEADDR, true);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true);
            try {
                ChannelFuture f = bootstrap.bind("0.0.0.0", port).sync();
                log.info("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉 NettyServer tcp{} started   telnet 127.0.0.1:8901 🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉", port);
                f.channel().closeFuture().sync();
                log.info("NettyServer {} closed", port);
            } catch (Exception e) {
                log.error("😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭 NettyServer tcp{} start failed 😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭", port, e);
            } finally {
                destroy();
            }
        });

    }

    @PreDestroy
    public void destroy() {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        serverStartor.shutdown();
    }

    protected ChannelHandler initChildChannelHandler() {
        return new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) {
                ChannelPipeline pipeline = channel.pipeline();
                // 检测远端是否存活 超过时间，触发UserEventTriggered事件
                // 来检测客户端的读取超时的，
                // 第一个参数是指定读操作空闲秒数，多久没有接收到信息，READER_IDLE（未读操作状态）
                // 第二个参数是指定写操作的空闲秒数，多久没有发送信息，WRITER_IDLE
                // 第三个参数是指定读写空闲秒数， ALL_IDLE
                // 所以我们只需要在自己的handler中截获该事件，然后发起相应的操作即可（比如说发起心跳操作）。
                pipeline.addLast(new IdleStateHandler(15, 15, 10, TimeUnit.SECONDS));
                pipeline.addLast(new ReadTimeoutHandler(SESION_RECYCLER_EXPIRE));
                pipeline.addLast(new TCPDispatcher());
                pipeline.addLast(new HttpServerCodec());
                pipeline.addLast(new HttpObjectAggregator(512 * 1024 * 1024));
                pipeline.addLast(httpDispatcher);
                pipeline.addLast(http);
                pipeline.addLast(webSocket);
                pipeline.addLast(tcp);
            }
        };
    }
}

@Slf4j
class TCPDispatcher extends ByteToMessageDecoder {


    public TCPDispatcher() {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ChannelPipeline pipeline = ctx.pipeline();
        if (!isHttp(in)) {
            pipeline.remove(HttpServerCodec.class);
            pipeline.remove(HttpObjectAggregator.class);
            pipeline.remove(HttpDispatcher.class);
            pipeline.remove(HttpHandler.class);
            pipeline.remove(WebSocketHandler.class);
        } else {
            pipeline.remove(TCPHandler.class);
        }
        ctx.pipeline().remove(this.getClass());
    }

    /**
     * 判断请求是否是HTTP请求
     *
     * @return
     */
    private boolean isHttp(ByteBuf in) {
        int magic1 = in.getUnsignedByte(in.readerIndex());
        int magic2 = in.getUnsignedByte(in.readerIndex() + 1);
        in.resetReaderIndex();
        return magic1 == 'G' && magic2 == 'E' || // GET
                magic1 == 'P' && magic2 == 'O' || // POST
                magic1 == 'P' && magic2 == 'U' || // PUT
                magic1 == 'H' && magic2 == 'E' || // HEAD
                magic1 == 'O' && magic2 == 'P' || // OPTIONS
                magic1 == 'P' && magic2 == 'A' || // PATCH
                magic1 == 'D' && magic2 == 'E' || // DELETE
                magic1 == 'T' && magic2 == 'R' || // TRACE
                magic1 == 'C' && magic2 == 'O';   // CONNECT
    }
}
