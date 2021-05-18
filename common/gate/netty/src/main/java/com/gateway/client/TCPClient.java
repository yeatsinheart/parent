package com.gateway.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TCPClient {
    public static ChannelFuture future;

    public static void startClient() {
        new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()).execute(() -> {
        });
    }

    public static void main(String[] args) throws InterruptedException {
        // 首先，netty通过ServerBootstrap启动服务端
        Bootstrap client = new Bootstrap();
        //第1步 定义线程组，处理读写和链接事件，没有了accept事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);
        //第2步 绑定客户端通道
        client.channel(NioSocketChannel.class);
        client.option(ChannelOption.TCP_NODELAY, true);
        //第3步 给NIoSocketChannel初始化handler， 处理读写事件
        client.handler(new ChannelInitializer<NioSocketChannel>() {  //通道是NioSocketChannel
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                //字符串编码器，一定要加在SimpleClientHandler 的上面
                ch.pipeline().addLast(new StringEncoder());
                //ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                //找到他的管道 增加他的handler
                ch.pipeline().addLast(new SimpleClientHandler());
            }
        });
        //连接服务器
        ChannelFuture future = client.connect("localhost", 8901).sync();
        //发送数据给服务器
        Channel session = future.channel();
        session.writeAndFlush("test connect");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            String msg = "ssss" + i;
            session.writeAndFlush(msg);
        }
        //接收服务端返回的数据
       /* AttributeKey<String> key = AttributeKey.valueOf("ServerData");
        Object result = session.attr(key).get();
        System.out.println(result.toString());*/
        //当通道关闭了，就继续往下走
        session.closeFuture().sync();
    }

    public static class SimpleClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof ByteBuf) {

                ByteBuf buf = (ByteBuf) msg;
                byte[] req = new byte[buf.readableBytes()];
                buf.readBytes(req);
                String body = new String(req, StandardCharsets.UTF_8.name());
                System.out.println("receive response:" + body);
                System.out.println("返回咯🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
                AttributeKey<String> key = AttributeKey.valueOf("ServerData");
                ctx.channel().attr(key).set("客户端处理完毕");
            }
            //把客户端的通道关闭
            //ctx.channel().close();
        }
    }
}
