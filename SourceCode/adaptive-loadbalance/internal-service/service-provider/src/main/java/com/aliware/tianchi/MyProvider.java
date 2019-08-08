package com.aliware.tianchi;

import com.aliware.tianchi.policy.BaseConfig;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ArgumentConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.service.CallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author guohaoice@gmail.com
 */
public class MyProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyProvider.class);

    private static ApplicationConfig application = new ApplicationConfig();
    private static RegistryConfig registry = new RegistryConfig();
    private static ProtocolConfig protocol = new ProtocolConfig();

    public static void main(String[] args) throws InterruptedException {

        BaseConfig config = BaseConfig.loadConf();
        // 当前应用配置
        application.setName("service-provider");
        application.setQosEnable(false);

        // 连接注册中心配置
        registry.setAddress("N/A");

        // 服务提供者协议配置
        protocol.setName("dubbo");
        protocol.setPort(config.getPort());
        protocol.setThreads(config.getMaxThreadCount());
        protocol.setHost("0.0.0.0");

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        exportHashService(config.getConfigs());

        try {
            exportCallbackServiceIfNeed();
        } catch (Exception e) {
            LOGGER.error("exportCallbackServiceIfNeed failed", e);
        }

        Thread.currentThread().join();
    }

    private static void exportHashService(List<ThrashConfig> configs) {
        // 服务提供者暴露服务配置
        ServiceConfig<HashInterface> service =
                new ServiceConfig<>();
        Map<String, String> attributes = new HashMap<>();
        attributes.put("heartbeat", "0");
        service.setParameters(attributes);
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setInterface(HashInterface.class);
        service.setRef(new HashServiceImpl(System.getProperty("salt"), configs));

        // 暴露及注册服务
        service.export();
    }

    private static void exportCallbackServiceIfNeed() {
        Set<String> supportedExtensions =
                ExtensionLoader.getExtensionLoader(CallbackService.class).getSupportedExtensions();
        if (supportedExtensions != null && supportedExtensions.size() == 1) {
            CallbackService callbackService =
                    ExtensionLoader.getExtensionLoader(CallbackService.class)
                            .getExtension(supportedExtensions.iterator().next());
            ServiceConfig<CallbackService> callbackServiceServiceConfig = new ServiceConfig<>();
            Map<String, String> attributes = new HashMap<>();
            attributes.put("heartbeat", "0");
            callbackServiceServiceConfig.setParameters(attributes);
            callbackServiceServiceConfig.setApplication(application);
            callbackServiceServiceConfig.setRegistry(registry);
            callbackServiceServiceConfig.setProtocol(protocol);
            callbackServiceServiceConfig.setInterface(CallbackService.class);
            callbackServiceServiceConfig.setRef(callbackService);
            callbackServiceServiceConfig.setCallbacks(1000);
            callbackServiceServiceConfig.setConnections(1);
            MethodConfig methodConfig = new MethodConfig();
            ArgumentConfig argumentConfig = new ArgumentConfig();
            argumentConfig.setCallback(true);
            argumentConfig.setIndex(1);
            methodConfig.setArguments(Collections.singletonList(argumentConfig));
            methodConfig.setName("addListener");
            callbackServiceServiceConfig.setMethods(Collections.singletonList(methodConfig));
            callbackServiceServiceConfig.export();
        }
    }
}
