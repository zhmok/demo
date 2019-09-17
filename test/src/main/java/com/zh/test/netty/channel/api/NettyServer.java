package com.zh.test.netty.channel.api;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 非阻塞版本
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {
        new NettyServer().server(8081);
    }

    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast("http-codec", new HttpServerCodec());
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                                    ctx.writeAndFlush(buf.duplicate());
                                            //.addListener(ChannelFutureListener.CLOSE);
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
                                    ChannelFuture cf = ctx.write(buf);
                                    System.out.println(buf.toString());
                                    cf.addListener(new ChannelFutureListener() {
                                        @Override
                                        public void operationComplete(ChannelFuture future) {
                                            if (future.isSuccess()) {
                                                System.out.println("write ok");
                                            } else {
                                                System.out.println("write error");
                                                future.cause().printStackTrace();
                                            }
                                        }

                                    });
                                }

                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.flush();
                                    ctx.close();
                                }
                            });

                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
