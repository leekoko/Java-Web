package com.aliware.tianchi;

import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Facade
 *
 * @author guohaoice@gmail.com
 */
public class HashServiceImpl implements HashInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(HashServiceImpl.class);

    private final String salt;
    private final AtomicBoolean init = new AtomicBoolean(false);
    private final List<ThrashConfig> configs;
    private final InternalSemaphore permit;
    private volatile ThrashConfig currentConfig;
    private Random rng = new Random(2019);
    private ScheduledExecutorService scheduler =
            new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("HashService-Config-Refresher"));

    HashServiceImpl(String salt, List<ThrashConfig> configs) {
        this.salt = salt;
        this.currentConfig = ThrashConfig.INIT_CONFIG;
        this.permit = new InternalSemaphore(currentConfig.max_concurrent);
        this.configs = Collections.unmodifiableList(configs);
    }

    @Override
    public Integer hash(String input) {
        long st = System.currentTimeMillis();
        if (!init.get()) {
            if (init.compareAndSet(false, true)) {
                int startTime = 30;
                int totalPermit = ThrashConfig.INIT_CONFIG.max_concurrent;
                for (ThrashConfig thrashConfig : configs) {
                    final int tmpTotal = totalPermit;
                    scheduler.schedule(() -> refresh(thrashConfig, tmpTotal), startTime, TimeUnit.SECONDS);
                    startTime += thrashConfig.durationInSec;
                    totalPermit = thrashConfig.max_concurrent;
                }
            }
        }
        try {
            permit.acquire();
            long rtt = nextRTT();
            Thread.sleep(rtt);
            return (input + salt).hashCode();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            long cost = System.currentTimeMillis() - st;
            LOGGER.info("HashService cost:{} ms to handle request", cost);
            permit.release();
        }
        throw new IllegalStateException("Unexpected exception");
    }

    private void refresh(ThrashConfig thrashConfig, int totalPermit) {
        this.currentConfig = thrashConfig;
        int permitChange = totalPermit - thrashConfig.max_concurrent;
        if (permitChange != 0) {
            if (permitChange > 0) {
                permit.reducePermit(permitChange);
            } else {
                permit.addPermit(Math.abs(permitChange));
            }
        }

        LOGGER.info("Refresh config to {}", thrashConfig);
    }

    private long nextRTT() {
        double u = rng.nextDouble();
        int x = 0;
        double cdf = 0;
        while (u >= cdf) {
            x++;
            cdf = 1 - Math.exp(-1.0D * 1 / currentConfig.avg_rtt * x);
        }
        return x;
    }
}
