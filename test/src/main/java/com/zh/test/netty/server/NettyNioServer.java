package com.zh.test.netty.server;

import com.zh.test.netty.echo.EchoServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 非阻塞版本
 *
 */
public class NettyNioServer {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage:" + EchoServer.class.getSimpleName() + "<port>");
        }
//        int port = Integer.parseInt(args[0]);
        int port = 8081;
        new NettyNioServer().server(port);
    }

    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buf.duplicate());
                                }
                            });

                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                    ByteBuf m = (ByteBuf) msg;

                                    System.out.println(m.toString(CharsetUtil.UTF_8));

                                    FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                            HttpResponseStatus.OK,
                                            buf);

                                    resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");

                                    // 2.发送
                                    // 注意必须在使用完之后，close channel
                                    ctx.writeAndFlush(m).addListener(ChannelFutureListener.CLOSE);
                                    m.release();
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
