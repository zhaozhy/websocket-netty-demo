package com.websocket.nettyserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class WebSocketHandle  extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        Channel channel=channelHandlerContext.channel();
        System.out.println(channel.remoteAddress() + ": "+textWebSocketFrame.text());
        channel.writeAndFlush(new TextWebSocketFrame("服务端消息："+ LocalDateTime.now()+"客户端说："+textWebSocketFrame.text()));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelId"+ctx.channel().id().asLongText()+"用户下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelId"+ctx.channel().id().asLongText()+"加入聊天");
    }
}
