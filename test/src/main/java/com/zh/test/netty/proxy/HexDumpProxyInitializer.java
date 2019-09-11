package com.zh.test.netty.proxy;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HexDumpProxyInitializer extends ChannelInitializer<SocketChannel> {
    private final String remoteHost;
    private final int remotePort;

    public HexDumpProxyInitializer(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        System.out.println("this." + this);
//        socketChanel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
//        socketChanel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65535));
//        socketChanel.pipeline().addLast("http-encoder", new HttpResponseEncoder());
//        socketChanel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
//        socketChanel.pipeline().addLast("http-server", new NettyHttpServerHandler());
        ch.pipeline().addLast(
                new LoggingHandler(LogLevel.INFO),
                new HttpRequestDecoder(),
                new HexDumpProxyFrontendHandler(remoteHost, remotePort),
                new HttpObjectAggregator(65535),
                new HttpRequestEncoder()
        );
    }

}