package com.gateway.server;

import com.base.utils.NamingThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
@Slf4j
public abstract class AbstractNettyServer<C extends Channel> {
    public int BODY_LENGTH = 6 * 1024; // 6K bytes
    //Runtime.getRuntime().availableProcessors()
    //Runtime.getRuntime().availableProcessors() * 2,
    @Value("${netty.readwirte.idle.time:15}")
    protected int readWriteIdleTime;
    @Value("${netty.read.idle.time:15}")
    protected int readIdleTime;
    @Value("${netty.wirte.idle.time:15}")
    protected int writeIdleTime;
    @Value("${netty.boss.num:1}")
    protected int bossNum;
    @Value("${netty.worker.num:1}")
    protected int workerNum;
    protected ServerBootstrap bootstrap;
    protected EventLoopGroup bossGroup;
    protected EventLoopGroup workerGroup;


    @PostConstruct
    public void startServer() {

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
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast(new IdleStateHandler(readIdleTime, writeIdleTime, readWriteIdleTime, TimeUnit.SECONDS));
                addHandler(pipeline);
            }
        });
        bootstrap.group(bossGroup, workerGroup);

        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);
        List<Integer> p = ports();
        if (CollectionUtils.isEmpty(p)) {
            throw new RuntimeException("没有端口怎么启动");
        }
        p.forEach(port -> {
            new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new NamingThreadFactory(this.getClass().getSimpleName() + ".java")).execute(() -> {
                startPort(port);
            });
        });
    }

    protected void startPort(int port) {
        try {
            ChannelFuture f = bootstrap.bind("0.0.0.0", port).sync();
            //telnet 127.0.0.1:8901
            //nc -u 127.0.0.1:7901
            log.info("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉 NettyServer {} started 🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉", port);
            f.channel().closeFuture().sync();
            log.error("NettyServer {} closed", port);
        } catch (Exception e) {
            log.error("😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭 NettyServer {} failed 😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭", port, e);
        } finally {
            destroy();
        }
    }

    /**
     * 指定绑定的端口
     */
    public abstract List<Integer> ports();

    /**
     * 指定线程
     */
    public abstract void initThread();

    /**
     * 指定Channel
     */
    public abstract void assignChannel();

    /**
     * 指定参数
     */
    public abstract void assignOption();

    /**
     * 指定处理器
     */
    public abstract void addHandler(ChannelPipeline pipeline);


    @PreDestroy
    public void destroy() {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        //threadPool.unLimitedExecutor.shutdown();
    }
}
