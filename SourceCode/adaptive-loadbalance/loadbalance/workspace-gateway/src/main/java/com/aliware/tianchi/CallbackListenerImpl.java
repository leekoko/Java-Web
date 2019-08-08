package com.aliware.tianchi;

import org.apache.dubbo.rpc.listener.CallbackListener;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author daofeng.xjf
 * <p>
 * 客户端监听器
 * 可选接口
 * 用户可以基于获取获取服务端的推送信息，与 CallbackService 搭配使用
 */
public class CallbackListenerImpl implements CallbackListener {

    public static final Map<String, ProviderQuota.Quota> map = new ConcurrentHashMap<>(3);

    @Override
    public void receiveServerMsg(String msg) {
        if (map.size() < 3){
            synchronized (CallbackListenerImpl.class) {
                if (map.size() < 3){
                    String[] split = msg.split(",");
                    if (split.length > 0) {
                        String quotaName = split[0];
                        int maxTaskCount = Integer.parseInt(split[1]);
                        ProviderQuota.Quota quota = new ProviderQuota.Quota();
                        quota.quotaName = quotaName;
                        quota.maxTaskCount = maxTaskCount;
                        map.put(quotaName, quota);
                    }
                }
            }
        }
    }
}
