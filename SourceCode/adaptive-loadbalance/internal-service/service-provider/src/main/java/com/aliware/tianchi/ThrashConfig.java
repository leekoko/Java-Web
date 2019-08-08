package com.aliware.tianchi;

/**
 * @author guohaoice@gmail.com
 */
public class ThrashConfig {
    static final ThrashConfig INIT_CONFIG = new ThrashConfig(1600, 50);
    final long durationInSec = 6;
    final double avg_rtt;
    final int max_concurrent;

    public ThrashConfig(int max_concurrent, double avg_rtt) {
        this.avg_rtt = avg_rtt;
        this.max_concurrent = max_concurrent;
    }

    @Override
    public String toString() {
        return "Duration :" + durationInSec + " averageRTT:" + avg_rtt + " maxConcurrency:" + this.max_concurrent;
    }
}
