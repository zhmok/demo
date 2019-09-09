package com.zh.test.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;

public class MultipleClient {

    public static void main(String[] args) throws Exception {
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            Bootstrap b = new Bootstrap(); // (1)
//            b.group(workerGroup); // (2)
//            b.channel(NioSocketChannel.class); // (3)
//            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new HttpRequestEncoder());
//                    ch.pipeline().addLast(new HttpRequestDecoder());
//                }
//            });
//
//            // Start the client.
//            ChannelFuture f = b.connect("127.0.0.1", 8080).sync(); // (5)
//            ChannelFuture f1 = b.connect("127.0.0.1", 8080).sync(); // (5)
//            FullHttpRequest req = null;
//
//
//            f.channel().write((req = getReq()));
//            f.channel().flush();
//            f.channel().close();
//            System.out.println("f:" + f);
//            System.out.println("f1:" + f1);
//            // Wait until the connection is closed.
//            f.channel().closeFuture().sync();
//            f1.channel().closeFuture().sync();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } finally {
//            workerGroup.shutdownGracefully();
//        }



//        new MultipleClient().connect("127.0.0.1",8080);
    }

    public static FullHttpRequest getReq() throws UnsupportedEncodingException {
        String msg = "Are you ok?";

        DefaultFullHttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_0,
                HttpMethod.GET, "/test",
                Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));
        request.headers().set(HttpHeaders.Names.HOST, "127.0.0.1");
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
        request.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/json");
        return request;

    }


    public void connect(String host, int port) throws Exception {

        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {

            Bootstrap b = new Bootstrap();

            b.group(workerGroup);

            b.channel(NioSocketChannel.class);

            b.option(ChannelOption.SO_KEEPALIVE, true);

            b.handler(new ChannelInitializer<SocketChannel>() {

                @Override

                public void initChannel(SocketChannel ch) throws Exception {

                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码

                    ch.pipeline().addLast(new HttpResponseDecoder());

                    // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码

                    ch.pipeline().addLast(new HttpRequestEncoder());

                    ch.pipeline().addLast(new HttpClientInboundHandler());

                }

            });


            // Start the client.

            ChannelFuture f = b.connect(host, port).sync();


            URI uri = new URI("http://127.0.0.1:8844");

            String msg = "Are you ok?";

            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,

                    uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));


            // 构建http请求

            request.headers().set(HttpHeaders.Names.HOST, host);

            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());

            // 发送http请求

            f.channel().write(request);

            f.channel().flush();

            f.channel().closeFuture().sync();

        } finally {

            workerGroup.shutdownGracefully();

        }

    }
}
class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {



    @Override

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof HttpResponse)

        {

            HttpResponse response = (HttpResponse) msg;

            System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));

        }

        if(msg instanceof HttpContent)

        {

            HttpContent content = (HttpContent)msg;

            ByteBuf buf = content.content();

            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));

            buf.release();

        }

    }

}