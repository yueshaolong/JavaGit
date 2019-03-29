package com.ysl.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class NettyClient {
    public static Logger logger = Logger.getLogger(NettyClient.class);
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel channel) throws Exception {
                                ChannelPipeline p = channel.pipeline();
                                p.addLast(
//                                        new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Unpooled.copiedBuffer(ByteMessage.SEPARATOR.getBytes())),
                                        new IdleStateHandler(0, 180, 0, TimeUnit.SECONDS),
                                        new ByteArrayEncoder(),
                                        new ByteArrayDecoder(),
                                        new ClientHandler());
                            }
                        });

                ChannelFuture future = bootstrap.connect("192.168.1.208", 9966);
                future.addListener(channelFutureListener);
            }
        };
        new Thread(runnable).start();
    }

    private static ChannelFutureListener channelFutureListener = new ChannelFutureListener() {
        public void operationComplete(ChannelFuture f) throws Exception {
            if (f.isSuccess()) {
                logger.info("for信令服务，连接服务器成功");
            } else {
                logger.info("for信令服务，连接服务器失败");
            }
        }
    };
}
