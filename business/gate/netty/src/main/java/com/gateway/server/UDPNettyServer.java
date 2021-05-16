package com.gateway.server;

import com.common.utils.NamingThreadFactory;
import com.gateway.server.handler.UDPHandler;
import com.gateway.server.udp.UDPServerChannel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
@Slf4j
@Component("udpServer")
public class UDPNettyServer {
    public static int TCP_FRAME_FIXED_HEADER_LENGTH = 4;     // 4 bytes
    public static int TCP_FRAME_MAX_BODY_LENGTH = 6 * 1024; // 6K bytes
    @Value("${session.online.time}")
    public int expire = 20;//10;
    @Autowired
    UDPHandler udp;
    private ExecutorService serverStartor;
    private ServerBootstrap bootstrap;
    //Runtime.getRuntime().availableProcessors()
    private EventLoopGroup bossGroup;
    ;
    //Runtime.getRuntime().availableProcessors() * 2,
    private EventLoopGroup workerGroup;
    @Value("${net.udp.ports}")
    private int port;


    @PostConstruct
    public void start() {
        bootstrap = new ServerBootstrap();
        serverStartor = Executors.newSingleThreadExecutor(new NamingThreadFactory("p-" + port));
        serverStartor.execute(() -> {
            bossGroup = new NioEventLoopGroup(new NamingThreadFactory(port + "-p"));
            workerGroup = new DefaultEventLoopGroup(new NamingThreadFactory(port + "-c"));
            bootstrap.group(bossGroup, workerGroup)
                    .channel(UDPServerChannel.class)
                    .childHandler(new ProxyChannelInitializer());
            //bootstrap.option(ChannelOption.SO_BACKLOG, 5000).option(ChannelOption.SO_REUSEADDR, true);
//        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
//                .childOption(ChannelOption.TCP_NODELAY, true);
            try {
                InetAddress address = InetAddress.getLocalHost();

                /**
                 *  绑定100个端口号
                 */
                ChannelFuture f = bootstrap.bind("0.0.0.0", port).sync();
                log.info("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉 NettyServer udp{} started nc -u 127.0.0.1 7901 🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉", port);
                f.channel().closeFuture().sync();
                log.info("NettyServer {} closed", port);
            } catch (Exception e) {
                log.error("😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭 NettyServer udp{} start failed 😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭", port, e);
            } finally {
                destroy();
            }
        });

    }

    private void init() {


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

    private class ProxyChannelInitializer extends ChannelInitializer<Channel> {
        public ProxyChannelInitializer() {
        }

        @Override
        protected void initChannel(Channel channel) throws Exception {
            // ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
            //ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);
            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addLast("ping", new IdleStateHandler(15, 15, 10, TimeUnit.SECONDS));
            pipeline.addLast(new ReadTimeoutHandler(expire));
            pipeline.addLast(udp);

            /*channel.pipeline().addLast(
                    new LoggingHandler(UDPNettyServer.class, LogLevel.DEBUG),
                    new HttpServerCodec(),
                    new HttpObjectAggregator(512 * 1024 * 1024),
                    webSocketHandler);*/
            //ch.pipeline().addLast("response", new ResponseHandler());
        }
    }

}
