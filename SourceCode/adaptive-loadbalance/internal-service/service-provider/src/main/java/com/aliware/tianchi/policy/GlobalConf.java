package com.aliware.tianchi.policy;

import com.aliware.tianchi.ThrashConfig;

import java.util.List;

/**
 * @author guohaoice@gmail.com
 */
public class GlobalConf {
    public final List<ThrashConfig> small;
    public final List<ThrashConfig> medium;
    public final List<ThrashConfig> large;

    public GlobalConf(List<ThrashConfig> small, List<ThrashConfig> medium, List<ThrashConfig> large) {
        this.small = small;
        this.medium = medium;
        this.large = large;
    }
}
