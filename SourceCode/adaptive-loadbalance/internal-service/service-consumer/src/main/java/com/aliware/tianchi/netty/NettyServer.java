package com.aliware.tianchi.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup(1, new NamedThreadFactory("Dubbo-Proxy-Boss"));
        workerGroup =
                new NioEventLoopGroup(
                        Runtime.getRuntime().availableProcessors() * 2,
                        new NamedThreadFactory("Dubbo-Proxy-Worker"));
        HttpProcessHandler handler = new HttpProcessHandler();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<Channel>() {
                            @Override
                            protected void initChannel(Channel ch) {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast(new HttpServerCodec());
                                pipeline.addLast(new HttpObjectAggregator(0));
                                pipeline.addLast(handler);
                            }
                        })
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        String host = "0.0.0.0";
        int port = 8087;
        try {
            ChannelFuture f = bootstrap.bind(host, 8087).sync();
            logger.info("Dubbo proxy started, host is {}, port is {}.", host, port);
            f.channel().closeFuture().sync();
            logger.info("Dubbo proxy closed, host is {} , 8087 is {}.", host, port);
        } catch (InterruptedException e) {
            logger.error("DUBBO proxy start failed", e);
        } finally {
            destroy();
        }
    }

    public void destroy() {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
    }
}
