package com.zh.test.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage" + EchoServer.class.getSimpleName() + "<port>");
        }
//        int port = Integer.parseInt(args[0]);
        int port = 8080;
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class)
                    /*
                     * 使用指定的 端口设置套 接字地址
                     */
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });

            /*
             * 异步地绑定服务器； 调用sync()方法阻塞 等待直到绑定完成
             */
            ChannelFuture f = b.bind().sync();
            /*
             * 获取 Channel 的 CloseFuture，并 且阻塞当前线 程直到它完成
             */
            f.channel().closeFuture().sync();

        } finally {
            // 关闭 EventLoopGroup， 释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
