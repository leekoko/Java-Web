package com.aliware.tianchi;

import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.context.ConfigManager;
import org.apache.dubbo.rpc.listener.CallbackListener;
import org.apache.dubbo.rpc.service.CallbackService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author daofeng.xjf
 * <p>
 * 服务端回调服务
 * 可选接口
 * 用户可以基于此服务，实现服务端向客户端动态推送的功能
 */
public class CallbackServiceImpl implements CallbackService {

    /**
     * key: listener type
     * value: callback listener
     */
    private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<>();

    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key, listener);
        ProtocolConfig dubbo = ConfigManager.getInstance().getProtocols().get("dubbo");
        ProviderQuota.INSTANCE.quotaName = System.getProperty("quota");
        ProviderQuota.INSTANCE.maxTaskCount = dubbo.getThreads();
        String msg = ProviderQuota.INSTANCE.toString();
        listener.receiveServerMsg(msg);
    }
}
