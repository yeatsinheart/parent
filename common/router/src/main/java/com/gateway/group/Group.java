package com.gateway.group;

import com.gateway.response.Flush;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Group {
    //channels保存所有链接进来的channel
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void all(Channel channel, String msg) {
        Flush.flush((ChannelHandlerContext) channels, msg, false);
    }

    public void others(Channel channel, String msg) {
        channels.forEach(ch -> {
            if (channel != ch) {
                Flush.flush((ChannelHandlerContext) ch, msg, false);
            }
        });
    }

    public void join(Channel channel) {
        channels.add(channel);
    }
}
