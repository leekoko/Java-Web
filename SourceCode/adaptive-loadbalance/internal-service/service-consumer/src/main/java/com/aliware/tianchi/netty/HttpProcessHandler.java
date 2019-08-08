package com.aliware.tianchi.netty;

import com.aliware.tianchi.HashInterface;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.INTERNAL_SERVER_ERROR;

@ChannelHandler.Sharable
public class HttpProcessHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    static final ApplicationConfig application = new ApplicationConfig();
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpProcessHandler.class);
    private volatile boolean init = false;
    private HashInterface hashInterface;
    private InitProviderService initService = new InitProviderService();
    private String salt = System.getProperty("salt");

    HttpProcessHandler() {
        this.hashInterface = getServiceStub();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {
        long start = System.currentTimeMillis();
        String content = RandomStringUtils.randomAlphanumeric(16);
        int expected = (content + salt).hashCode();
        if (!init) {
            init();
        }

        hashInterface.hash(content);
        CompletableFuture<Integer> result = RpcContext.getContext().getCompletableFuture();
        result.whenComplete((actual, t) -> {
            if (t == null && actual.equals(expected)) {
                FullHttpResponse ok =
                        new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer("OK\n", CharsetUtil.UTF_8));
                ok.headers().add(HttpHeaderNames.CONTENT_LENGTH, 3);
                ctx.writeAndFlush(ok);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Request result:success cost:{} ms", System.currentTimeMillis() - start);
                }
            } else {
                FullHttpResponse error =
                        new DefaultFullHttpResponse(HTTP_1_1, INTERNAL_SERVER_ERROR);
                error.headers().add(HttpHeaderNames.CONTENT_LENGTH, 0);
                ctx.writeAndFlush(error);
                LOGGER.info("Request result:failure cost:{} ms", System.currentTimeMillis() - start, t);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("Channel error", cause);
//        ctx.close();
    }


    private HashInterface getServiceStub() {
        application.setName("service-gateway");

        // 直连方式，不使用注册中心
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("N/A");

        ReferenceConfig<HashInterface> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(HashInterface.class);
        List<URL> urls = reference.toUrls();
        Map<String, String> attributes = new HashMap<>();
        attributes.put("loadbalance", "user");
        attributes.put("async", "true");
        attributes.put(Constants.HEARTBEAT_KEY, "0");
        attributes.put(Constants.RECONNECT_KEY, "false");
        urls.addAll(initService.buildUrls(HashInterface.class.getName(), attributes));
        return reference.get();
    }

    private synchronized void init() {
        if (init) {
            return;
        }

        init = true;

        initService.doInit();

    }


}
