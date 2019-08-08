package com.aliware.tianchi;

import com.sun.management.OperatingSystemMXBean;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.lang.management.ManagementFactory;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public enum CpuMonitor {
    INSTANCE;

    private final OperatingSystemMXBean osMBean;
    private long lastCPUTimeNs;
    private long lastSampleTimeNs;
    private final int cpuCoreNum;


    CpuMonitor() {
        this.cpuCoreNum = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
        this.osMBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        lastCPUTimeNs = this.getCpuTime();
        this.lastSampleTimeNs = System.nanoTime();
    }

    public long getCpuTime() {
        return osMBean.getProcessCpuTime();
    }

    public int getCPUSize() {
        return Runtime.getRuntime().availableProcessors();
    }


    public static void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpClientInboundHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();

            URI uri = new URI("http://127.0.0.1:8087/call");
            String msg = "";
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

    static class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.print("success!~!~");
            if (msg instanceof HttpResponse) {
                HttpResponse response = (HttpResponse) msg;
                System.out.println("HTTP_CODE:" + response.getStatus());

            }
            if (msg instanceof HttpContent) {
                HttpContent content = (HttpContent) msg;
                ByteBuf buf = content.content();

                System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
                buf.release();
            }
            ctx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 16; i++) {
            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
                try {
                    connect("127.0.0.1", 8087);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },0,1, TimeUnit.SECONDS);
        }
    }
}
