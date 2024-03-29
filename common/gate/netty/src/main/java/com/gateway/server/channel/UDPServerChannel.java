/*
 * Copyright (C) 2020  即时通讯网(52im.net) & Jack Jiang.
 * The MobileIMSDK v5.x Project.
 * All rights reserved.
 *
 * > Github地址：https://github.com/JackJiang2011/MobileIMSDK
 * > 文档地址：  http://www.52im.net/forum-89-1.html
 * > 技术社区：  http://www.52im.net/
 * > 技术交流群：320837163 (http://www.52im.net/topic-qqgroup.html)
 * > 作者公众号：“【即时通讯技术圈】”，欢迎关注！
 * > 联系作者：  http://www.52im.net/thread-2792-1-1.html
 *
 * "即时通讯网(52im.net) - 即时通讯开发者社区!" 推荐开源工程。
 *
 * MBUDPServerChannel.java at 2020-8-22 16:00:59, code by Jack Jiang.
 */
package com.gateway.server.channel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.ServerSocketChannelConfig;
import io.netty.util.internal.PlatformDependent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.spi.SelectorProvider;
import java.util.LinkedHashMap;
import java.util.List;

public class UDPServerChannel extends AbstractNioMessageChannel implements ServerSocketChannel {
    protected final LinkedHashMap<InetSocketAddress, UDPChannel> channels = new LinkedHashMap<>();
    private final ChannelMetadata METADATA = new ChannelMetadata(true);
    private final UDPServerChannelConfig config;

    public UDPServerChannel() throws IOException {
        this(SelectorProvider.provider().openDatagramChannel(StandardProtocolFamily.INET));
    }

    protected UDPServerChannel(DatagramChannel datagramChannel) {
        super(null, datagramChannel, SelectionKey.OP_READ);
        this.config = new UDPServerChannelConfig(this, datagramChannel);
    }

    @Override
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override
    protected SocketAddress localAddress0() {
        return this.javaChannel().socket().getLocalSocketAddress();
    }

    @Override
    public InetSocketAddress remoteAddress() {
        return null;
    }

    @Override
    protected SocketAddress remoteAddress0() {
        return null;
    }

    @Override
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override
    public ServerSocketChannelConfig config() {
        return config;
    }

    /**
     * 判断监听是否已启动。
     *
     * @return true表示已已启动监听，否则未启动
     * @see DatagramChannel#isOpen()
     */
    @Override
    public boolean isActive() {
        return this.javaChannel().isOpen() && this.javaChannel().socket().isBound();
    }

    @Override
    protected DatagramChannel javaChannel() {
        return (DatagramChannel) super.javaChannel();
    }

    @Override
    protected void doBind(SocketAddress localAddress) throws Exception {
        javaChannel().socket().bind(localAddress);
    }

    @Override
    protected void doClose() throws Exception {
        // “关闭”所有客户端的伪连接Channel
        for (UDPChannel channel : channels.values())
            channel.close();

        javaChannel().close();
    }

    public void removeChannel(final Channel channel) {
        eventLoop().submit(() -> {
            InetSocketAddress remote = (InetSocketAddress) channel.remoteAddress();
            if (channels.get(remote) == channel) {
                channels.remove(remote);
            }
        });
    }

    @Override
    protected int doReadMessages(List<Object> list) {
        DatagramChannel javaChannel = javaChannel();
        RecvByteBufAllocator.ExtendedHandle allocatorHandle = (RecvByteBufAllocator.ExtendedHandle) unsafe().recvBufAllocHandle();
        ByteBuf buffer = allocatorHandle.allocate(config.getAllocator());
        allocatorHandle.attemptedBytesRead(buffer.writableBytes());

        boolean freeBuffer = true;
        try {
            // read message
            ByteBuffer nioBuffer = buffer.internalNioBuffer(buffer.writerIndex(), buffer.writableBytes());
            int nioPos = nioBuffer.position();

            InetSocketAddress inetSocketAddress = (InetSocketAddress) javaChannel.receive(nioBuffer);
            if (inetSocketAddress == null)
                return 0;

            allocatorHandle.lastBytesRead(nioBuffer.position() - nioPos);
            buffer.writerIndex(buffer.writerIndex() + allocatorHandle.lastBytesRead());

            // allocate new channel or use existing one and push message to it
            UDPChannel udpchannel = channels.get(inetSocketAddress);
            if ((udpchannel == null) || !udpchannel.isOpen()) {
                udpchannel = new UDPChannel(this, inetSocketAddress);
                channels.put(inetSocketAddress, udpchannel);
                list.add(udpchannel);

                udpchannel.addBuffer(buffer);
                freeBuffer = false;

                return 1;
            } else {
                udpchannel.addBuffer(buffer);
                freeBuffer = false;

                if (udpchannel.isRegistered())
                    udpchannel.read();

                return 0;
            }
        } catch (Throwable t) {
            PlatformDependent.throwException(t);
            return -1;
        } finally {
            if (freeBuffer)
                buffer.release();
        }
    }

    @Override
    protected boolean doWriteMessage(Object msg, ChannelOutboundBuffer buffer) throws Exception {
        DatagramPacket dpacket = (DatagramPacket) msg;
        InetSocketAddress recipient = dpacket.recipient();
        ByteBuf byteBuf = dpacket.content();
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0)
            return true;

        ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(
                byteBuf.readerIndex(), readableBytes);

        return javaChannel().send(internalNioBuffer, recipient) > 0;
    }

    @Override
    protected boolean doConnect(SocketAddress addr1, SocketAddress addr2) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doFinishConnect() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doDisconnect() {
        throw new UnsupportedOperationException();
    }
}
