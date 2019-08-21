# 中间件比赛

测试的数据是每秒1024个请求，执行60s，达到12w的QPS

1.权重轮询

首先是根据权重进行轮询，但由于随机的，所以会导致请求堆积。

2.动态权重

后来就加了个动态变化权重，当分配给某个服务，它的权重就-1，当该服务处理完进行反馈，权重+1

3.令牌队列

提前按照线程数设置令牌填满队列，每条数据服务的时候从中拿令牌，收到回复之后返回令牌。

## 最大剩余能力

```text
Q = R * W
```

耗费的时间是W，其处理的速率为R，面前排队的数量Q。根据R = Q / W，算出效率最高的服务，将请求分配过去。

1. 对每个Provider在Gateway端维护一个ProviderStatus。其中各自都有一个类型为`LinkedBlockingQueue[WorkRequest]`的workRequestQueue。仍然按照前面说的，注册的时候每个队列都发maxThreads数量的WorkRequest，直到完成初始化。

2. LoadBalancer选择的时候，比较每个Provider的workRequestQueue的大小，size最大的把请求发过去。

3. 请求处理完成后，发还workRequest给对应的ProviderStatus的workRequestQueue.

