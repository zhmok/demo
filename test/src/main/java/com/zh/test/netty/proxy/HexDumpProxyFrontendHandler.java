package com.zh.test.netty.proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

public class HexDumpProxyFrontendHandler extends ChannelInboundHandlerAdapter {


    private final String remoteHost;

    private final int remotePort;


    // As we use inboundChannel.eventLoop() when building the Bootstrap this does not need to be volatile as

    // the outboundChannel will use the same EventLoop (and therefore Thread) as the inboundChannel.

    private Channel outboundChannel;


    public HexDumpProxyFrontendHandler(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;

    }


    @Override

    public void channelActive(ChannelHandlerContext ctx) {
        final Channel inboundChannel = ctx.channel();
        // Start the connection attempt.
        Bootstrap b = new Bootstrap();
        b.group(inboundChannel.eventLoop())
                .channel(ctx.channel().getClass())
                .handler(new HexDumpProxyBackendHandler(inboundChannel))
//                .handler(new IdleStateHandler(0,0,0, TimeUnit.SECONDS))
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HttpClientCodec());
                    }
                })
                .option(ChannelOption.AUTO_READ, false);
        ChannelFuture f = b.connect(remoteHost, remotePort);
        outboundChannel = f.channel();
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    // connection complete start to read first data
                    inboundChannel.read();
                } else {
                    // Close the connection if the connection attempt has failed.
                    inboundChannel.close();
                }
            }
        });
    }


    @Override

    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        System.out.println(msg);
        if (outboundChannel.isActive()) {
            if(msg instanceof HttpRequest){
                DefaultHttpRequest request = (DefaultHttpRequest) msg;

                outboundChannel.write(request.toString());
            }else {

//                outboundChannel.write(msg);
            }

            if (msg instanceof LastHttpContent){
                LastHttpContent lastHttpContent = (LastHttpContent) msg;

                outboundChannel.writeAndFlush(lastHttpContent.content()).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) {
                        if (future.isSuccess()) {
                            // was able to flush out data, start to read the next chunk
                            ctx.channel().read();
                        } else {
                            future.cause().printStackTrace();
                            future.channel().close();
                        }
                    }
                });
            }
        }


    }


    @Override

    public void channelInactive(ChannelHandlerContext ctx) {
        if (outboundChannel != null) {
            closeOnFlush(outboundChannel);
        }

    }


    @Override

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        closeOnFlush(ctx.channel());

    }


    /**
     * Closes the specified channel after all queued write requests are flushed.
     */

    static void closeOnFlush(Channel ch) {
        if (ch.isActive()) {
            ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

}