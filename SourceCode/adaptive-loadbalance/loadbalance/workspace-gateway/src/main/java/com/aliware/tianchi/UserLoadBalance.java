package com.aliware.tianchi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author daofeng.xjf
 * <p>
 * 负载均衡扩展接口
 * 必选接口，核心接口
 * 此类可以修改实现，不可以移动类或者修改包名
 * 选手需要基于此类实现自己的负载均衡算法
 */
public class UserLoadBalance implements LoadBalance {

    public final static Map<Integer, String> portToQuota = new HashMap<>(3);
    public final static Map<Integer, Invoker> portToInvoker = new HashMap<>(3);

    public static volatile WeightRoundRobin wr = null;


    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {

        if (!WeightRoundRobin.ab.get()) {
            if (CallbackListenerImpl.map.size() == invokers.size()
                    && WeightRoundRobin.ab.compareAndSet(false, true)) {
                List<WeightRoundRobin.Server> servers = new ArrayList<>(invokers.size());
                for (Invoker<T> invoker : invokers) {
                    String quotaName = invoker.getUrl().getAddress().split(":")[0].split("-")[1];
                    int port = invoker.getUrl().getPort();
                    portToQuota.putIfAbsent(port, quotaName);
                    portToInvoker.putIfAbsent(port, invoker);

                    ProviderQuota.Quota quota = CallbackListenerImpl.map.get(quotaName);
                    KeyValPair.Entry entry = new KeyValPair.Entry();
                    entry.port = port;
                    entry.longAdder = new LongAdder();
                    entry.longAdder.add(quota.maxTaskCount);
                    WeightRoundRobin.portToSemaphore.put(entry);

                    WeightRoundRobin.portToThreadCount.putIfAbsent(port, quota.maxTaskCount);
                    WeightRoundRobin.Server server = new WeightRoundRobin.Server(port, quota.maxTaskCount);
                    servers.add(server);
                }
                wr = new WeightRoundRobin(servers);
            }
        }

        if (wr == null) {
            return null;
        }

        int port = wr.round().getPort();


        WeightRoundRobin.portToSemaphore.get(port).longAdder.decrement();


        return portToInvoker.get(port);
    }
}
