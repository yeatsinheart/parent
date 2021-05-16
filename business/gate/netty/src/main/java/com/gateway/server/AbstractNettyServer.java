package com.gateway.server;

import com.common.utils.NamingThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollChannelOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("ALL")
@Slf4j
public abstract class AbstractNettyServer<C extends Channel> {
    @Value("${session.maxsize}")
    public int BODY_LENGTH = 6 * 1024; // 6K bytes
    @Value("${session.onlinetime}")
    public int SESION_RECYCLER_EXPIRE;
    protected ServerBootstrap bootstrap;
    //Runtime.getRuntime().availableProcessors()
    protected int bossNum;
    protected EventLoopGroup bossGroup;
    //Runtime.getRuntime().availableProcessors() * 2,
    protected int workerNum;
    protected EventLoopGroup workerGroup;
    protected int port;
    protected ExecutorService serverStartor =Executors.newSingleThreadExecutor(new NamingThreadFactory("p-" + port));
    @PostConstruct
    public void startServer() {
        serverStartor.execute(() -> {
            initThread();
            assignChannel();
            if (Epoll.isAvailable()) {
                bootstrap.option(EpollChannelOption.SO_REUSEPORT, true);
            }
            assignOption();
            // worker线程使用
            bootstrap.childHandler(new ChannelInitializer<C>() {
                @Override
                protected void initChannel(C channel) throws Exception {
                    addHandler(channel);
                }
            });
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.option(ChannelOption.SO_BACKLOG, 5000)
                    .option(ChannelOption.SO_REUSEADDR, true);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true);
            try {
                ChannelFuture f = bootstrap.bind("0.0.0.0", port).sync();
                //telnet 127.0.0.1:8901
                //nc -u 127.0.0.1:7901
                log.info("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉 NettyServer {} started 🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉", port);
                f.channel().closeFuture().sync();
                log.info("NettyServer {} closed", port);
            } catch (Exception e) {
                log.error("😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭 NettyServer {} failed 😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭", port, e);
            } finally {
                destroy();
            }
        });
    }

    public abstract void initThread();

    public abstract void assignChannel();

    public abstract void assignOption();

    public abstract void addHandler(C channel);


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
}
