package com.aliware.tianchi.netty;

import com.aliware.tianchi.HashInterface;
import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.listener.CallbackListener;
import org.apache.dubbo.rpc.service.CallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author guohaoice@gmail.com
 */
public class InitProviderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpProcessHandler.class);
    private HashInterface[] stubs = getInitStubs();

    void doInit() {
        for (HashInterface stub : stubs) {
            stub.hash("hey");
            CompletableFuture<Integer> result = RpcContext.getContext().getCompletableFuture();
            result.whenComplete((a, t) -> {
                if (t == null) {
                    LOGGER.info("Init hash service successful. address:{} result:{}", RpcContext.getContext().getRemoteHost(), a, t);
                } else {
                    LOGGER.error("Init hash service failed. address:{} ", RpcContext.getContext().getRemoteHost(), t);
                }
            });
        }
        initCallbackListener();
    }


    private HashInterface[] getInitStubs() {
        HashInterface[] stubs = new HashInterface[3];
        ReferenceConfig<HashInterface> conf = createNewRefConf();
        conf.setUrl("dubbo://provider-small:20880");
        stubs[0] = conf.get();
        conf = createNewRefConf();
        conf.setUrl("dubbo://provider-medium:20870");
        stubs[1] = conf.get();
        conf = createNewRefConf();
        conf.setUrl("dubbo://provider-large:20890");
        stubs[2] = conf.get();
        return stubs;
    }


    private ReferenceConfig<HashInterface> createNewRefConf() {
        ApplicationConfig application = new ApplicationConfig();
        Map<String, String> attributes = new HashMap<>();
        attributes.put("async", "true");
        attributes.put(Constants.HEARTBEAT_KEY, "0");
        attributes.put(Constants.RECONNECT_KEY, "false");
        application.setName("service-gateway");
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("N/A");
        ReferenceConfig<HashInterface> reference = new ReferenceConfig<>();
        reference.setRegistry(registry);
        reference.setApplication(application);
        reference.setInterface(HashInterface.class);
        reference.setParameters(attributes);
        return reference;
    }

    private void initCallbackListener() {
        Set<String> supportedExtensions =
                ExtensionLoader.getExtensionLoader(CallbackListener.class).getSupportedExtensions();
        if (!supportedExtensions.isEmpty()) {
            Map<String, String> attributes = new HashMap<>();
            attributes.put("addListener.1.callback", "true");
            attributes.put("callbacks", "1000");
            attributes.put("connections", "1");
            attributes.put("dubbo", "2.0.2");
            attributes.put("dynamic", "true");
            attributes.put("generic", "false");
            attributes.put("interface", "org.apache.dubbo.rpc.service.CallbackService");
            attributes.put("methods", "addListener");
            attributes.put(Constants.HEARTBEAT_KEY, "0");
            attributes.put(Constants.RECONNECT_KEY, "false");

            for (String supportedExtension : supportedExtensions) {
                List<URL> urls = buildUrls(CallbackService.class.getName(), attributes);
                for (URL url : urls) {
                    CallbackListener extension =
                            ExtensionLoader.getExtensionLoader(CallbackListener.class)
                                    .getExtension(supportedExtension);

                    ReferenceConfig<CallbackService> reference = new ReferenceConfig<>();
                    reference.setApplication(HttpProcessHandler.application);
                    reference.setInterface(CallbackService.class);

                    reference.toUrls().add(url);
                    try {
                        reference.get().addListener(supportedExtension, extension);
                    } catch (Throwable t) {
                        LOGGER.error("Init callback listener failed. url:{}", url, t);
                    }
                    LOGGER.info("Init callback listener successful. extension:{} address:{}", extension.getClass().getSimpleName(), url.getAddress());
                }
            }
        }
    }

    List<URL> buildUrls(String interfaceName, Map<String, String> attributes) {
        List<URL> urls = new ArrayList<>();
        // 配置直连的 provider 列表
        urls.add(new URL(Constants.DUBBO_PROTOCOL, "provider-small", 20880, interfaceName, attributes));
        urls.add(new URL(Constants.DUBBO_PROTOCOL, "provider-medium", 20870, interfaceName, attributes));
        urls.add(new URL(Constants.DUBBO_PROTOCOL, "provider-large", 20890, interfaceName, attributes));
        return urls;
    }
}
