package com.zh.test.netty.proxy;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class MockHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof HttpRequest){
            HttpRequest request = (HttpRequest)msg;
            if(!"/actuator".equals(request.uri())){
                return;
            }
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                    request.protocolVersion(),
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer("hello", CharsetUtil.UTF_8)
            );
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }else{


            DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer("hello", CharsetUtil.UTF_8)
            );
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        cause.printStackTrace();
        ctx.close();
    }
}
