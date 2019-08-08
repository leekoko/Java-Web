package com.aliware.tianchi;

public enum ProviderQuota {
    INSTANCE;

    public String quotaName;
    public int maxTaskCount = 10;
    public int activeTaskCount;
    public int cpuMetric;

    ProviderQuota() {

    }

    @Override
    public String toString() {
        return quotaName+","+maxTaskCount+","+activeTaskCount+","+cpuMetric;
    }


    public static class Quota{
        public String quotaName;
        public int maxTaskCount;
        public int activeTaskCount;
        public int cpuMetric;
        public long heartbeat;
    }
}