package com.zh.test.netty.client.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;

public class HttpClient {
    static final int LOCAL_PORT = Integer.parseInt(System.getProperty("localPort", "8081"));
    static final String REMOTE_HOST = System.getProperty("remoteHost", "127.0.0.1");
    static final int REMOTE_PORT = Integer.parseInt(System.getProperty("remotePort", "8080"));

    public static void main(String[] args) throws InterruptedException {
        // Configure the bootstrap
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap b = new Bootstrap();
            b.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, false)
                    .option(ChannelOption.AUTO_READ, false)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();

                            //包含编码器和解码器
//                            p.addLast(new HttpClientCodec());
//                            //聚合
//                            p.addLast(new HttpObjectAggregator(1024 * 10 * 1024));
//                            //解压
//                            p.addLast(new HttpContentDecompressor());
//                            p.addLast(new ClientHandler());
//                            ch.pipeline().addLast(new HttpResponseDecoder());
                            // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
//                            ch.pipeline().addLast(new HttpRequestEncoder());

                            p.addLast(new ChannelInboundHandlerAdapter () {


                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) {

                                    ctx.write(msg);
                                    System.out.println("msg = " + msg);
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

                                @Override
                                public void channelReadComplete(ChannelHandlerContext ctx) {
                                    ctx.flush();
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                    // Close the connection when an exception is raised.
                                    cause.printStackTrace();
                                    ctx.close();
                                }
                            });
                            p.addLast(new ChannelOutboundHandlerAdapter(){
                                @Override
                                public void read(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("read");
                                    super.read(ctx);
                                }

                                @Override
                                public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                    System.out.println("write" + msg);
                                    super.write(ctx, msg, promise);
                                }

                                @Override
                                public void flush(ChannelHandlerContext ctx) throws Exception {
                                    super.flush(ctx);
                                }
                            });
                        }
                    });
            ;
            ChannelFuture c = b.connect(REMOTE_HOST, REMOTE_PORT).sync();
            HttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1,
                    HttpMethod.GET,
                    "/test",
                    Unpooled.EMPTY_BUFFER);
            request.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
//            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
            // Set some example cookies.
            request.headers().set(
                    HttpHeaderNames.COOKIE,
                    ClientCookieEncoder.STRICT.encode(
                            new DefaultCookie("my-cookie", "foo"),
                            new DefaultCookie("another-cookie", "bar")));
            c.channel().writeAndFlush(request).addListener(new ChannelFutureListener(){

                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()){
                        c.channel().read();
                    }
                }
            });
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}
