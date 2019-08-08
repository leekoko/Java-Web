package com.aliware.tianchi;

import com.aliware.tianchi.netty.NettyServer;

/**
 * Gateway 启动入口
 *
 * @author guohaoice@gmail.com
 */
public class MyConsumer {

    public static void main(String[] args) {
        NettyServer server = new NettyServer();

        server.start();
    }
}
