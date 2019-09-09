package com.zh.test.netty.echo;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 使用netty 编写简单的 echo 服务器
 *
 *<p>{@link ChannelHandler.Sharable} 标示一个Channel- Handler 可以被多 个Channel安全地 共享 </p>
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 对每个传入的消息都会调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server received:"+in.toString(CharsetUtil.UTF_8));

        // 将接收到的消息 写给发送者，而 不冲刷出站消息
        ctx.write(in);
    }


    /**
     * 最后一次对{@link #channelRead(ChannelHandlerContext, Object)}的调用
     * <p>是当前批量读取中的最后一条消息</p>
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到 远程节点，并且关 闭该 Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }


    /**
     * 在读取操作期间，有异常抛出时会调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常 栈跟踪
        cause.printStackTrace();
        // 关闭该
        // Channel
        ctx.close();
    }
}
