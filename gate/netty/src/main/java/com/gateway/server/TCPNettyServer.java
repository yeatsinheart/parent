package com.gateway.server;

import com.common.utils.NamingThreadFactory;
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
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component("tcpServer")
public class TCPNettyServer {
    @Value("${session.online.time}")
    public int SESION_RECYCLER_EXPIRE = 20;//10;
    public static int TCP_FRAME_FIXED_HEADER_LENGTH = 4;     // 4 bytes
    public static int TCP_FRAME_MAX_BODY_LENGTH = 6 * 1024; // 6K bytes
    public ExecutorService serverStartor;
    public ServerBootstrap bootstrap;
    //Runtime.getRuntime().availableProcessors()
    public EventLoopGroup bossGroup;
    //Runtime.getRuntime().availableProcessors() * 2,
    public EventLoopGroup workerGroup;
    @Value("${net.tcp.port}")
    public int port = 7901;

    @Autowired
    HttpDispatcher httpDispatcher;
    @Autowired
    private TCPHandler tcp;
    @Autowired
    private HttpHandler http;
    @Autowired
    private WebSocketHandler webSocket;

    @PostConstruct
    public void start() {
        bootstrap = new ServerBootstrap();
        serverStartor = Executors.newSingleThreadExecutor(new NamingThreadFactory("p-" + port));
        serverStartor.execute(() -> {
            init();
            try {
                ChannelFuture f = bootstrap.bind("0.0.0.0", port).sync();
                log.info("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉 NettyServer tcp{} started 🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉", port);
                f.channel().closeFuture().sync();
                log.info("NettyServer {} closed", port);
            } catch (Exception e) {
                log.error("😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭 NettyServer tcp{} start failed 😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭", port, e);
            } finally {
                destroy();
            }
        });

    }

    private void init() {
        int coreNum = Runtime.getRuntime().availableProcessors();
        bossGroup = new NioEventLoopGroup(new NamingThreadFactory(port + "-p"));
        workerGroup = new NioEventLoopGroup(new NamingThreadFactory(port + "-c"));
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(initChildChannelHandler());
        bootstrap.option(ChannelOption.SO_BACKLOG, 5000)
                .option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.TCP_NODELAY, true);
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
                pipeline.addLast(new ReadTimeoutHandler(SESION_RECYCLER_EXPIRE));
                pipeline.addLast(new TCPDispatcher(httpDispatcher, http, webSocket, tcp));
            }
        };
    }
}

@Slf4j
class TCPDispatcher extends ByteToMessageDecoder {
    private final HttpDispatcher httpDispatcher;
    private final TCPHandler tcpHandler;
    private final HttpHandler httpHandler;
    private final WebSocketHandler webSocketHandler;

    public TCPDispatcher(HttpDispatcher httpDispatcher, HttpHandler http, WebSocketHandler webSocket, TCPHandler tcp) {
        this.httpDispatcher = httpDispatcher;
        this.tcpHandler = tcp;
        this.httpHandler = http;
        this.webSocketHandler = webSocket;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (null == tcpHandler || null == httpHandler || null == webSocketHandler) {
            log.error("处理器为空tcp{}http{}ws{}", tcpHandler, httpHandler, webSocketHandler);
            return;
        }
        ChannelPipeline pipeline = ctx.pipeline();
        if (isHttp(in)) {
            pipeline.addLast(
                    new HttpServerCodec(),
                    new HttpObjectAggregator(512 * 1024 * 1024),
                    httpDispatcher,
                    httpHandler,
                    webSocketHandler);
        } else {
            pipeline.addLast(tcpHandler);
        }
        in.resetReaderIndex();
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
