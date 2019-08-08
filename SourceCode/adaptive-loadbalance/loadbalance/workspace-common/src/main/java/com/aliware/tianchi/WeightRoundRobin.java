package com.aliware.tianchi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class WeightRoundRobin {

    public final static KeyValPair portToSemaphore = new KeyValPair();
    public final static Map<Integer, Integer> portToThreadCount = new HashMap<>(3);
    public static AtomicBoolean ab = new AtomicBoolean(false);

    private List<Server> servers;

    public WeightRoundRobin(List<Server> servers) {
        this.servers = servers;
    }

    public Server round() {
        int mostActive = -1;
        int mostCount = 0;
        int mostIndex = 0;


        for (int i = 0; i < portToSemaphore.table.length; i++) {

            int left = portToSemaphore.table[i].longAdder.intValue();

            if (mostActive == -1 || left > mostActive) {
                mostActive = left;
                mostCount = 1;
                mostIndex = i;
            }
        }

        if (mostCount == 1) {
            return servers.get(mostIndex);
        }

        return servers.get(ThreadLocalRandom.current().nextInt(portToSemaphore.table.length));
    }

    public static class Server {
        private int port;
        private int weight;

        public Server(int port, int weight) {
            this.port = port;
            this.weight = weight;
        }
        public int getPort() {
            return port;
        }
    }
}
