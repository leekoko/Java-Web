package com.aliware.tianchi.policy;

import com.aliware.tianchi.ThrashConfig;
import com.google.gson.Gson;
import org.apache.dubbo.common.utils.IOUtils;
import org.apache.dubbo.common.utils.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author guohaoice@gmail.com
 */
public class BaseConfig {
    private final int maxThreadCount;
    private final int port;
    private List<ThrashConfig> thrashConfigs = new ArrayList<>(10);

    protected BaseConfig(int maxThreadCount, int port) {
        this.maxThreadCount = maxThreadCount;
        this.port = port;
    }

    public static BaseConfig loadConf() {
        Gson gson = new Gson();
        String origin = loadResourceAsString("provider-conf.json");
        GlobalConf globalConf = gson.fromJson(origin, GlobalConf.class);
        String env = System.getProperty("quota");
        if (StringUtils.isEmpty(env)) {
            System.out.println("[PROVIDER-SERVICE] No specific args found, use [DEFAULT] to run demo provider");
            env = "small";
        }
        BaseConfig config;
        switch (env) {
            case "small":
                config = new BaseConfig(200, 20880);
                config.thrashConfigs = globalConf.small;
                break;
            case "medium":
                config = new BaseConfig(450, 20870);
                config.thrashConfigs = globalConf.medium;
                break;
            case "large":
                config = new BaseConfig(650, 20890);
                config.thrashConfigs = globalConf.large;
                break;
            default:
                throw new IllegalStateException("[PROVIDER-SERVICE] Bad property: quota,value:" + env + ". valid value is small/medium/large");
        }

        return config;

    }

    private static String loadResourceAsString(String fileName) {
        ClassLoader classLoader = getClassLoader();

        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(fileName);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load provider-conf.json,cause:" + e.getMessage(), e);
        }

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            try {
                return IOUtils.read(new InputStreamReader(url.openStream())).replace("\n", "").trim();
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load provider-conf.json,cause:" + e.getMessage(), e);
            }
        }
        throw new IllegalStateException("Can not found provider-conf.json");
    }

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        return BaseConfig.class.getClassLoader();
    }

    public int getMaxThreadCount() {
        return maxThreadCount;
    }

    public int getPort() {
        return port;
    }

    public List<ThrashConfig> getConfigs() {
        return thrashConfigs;
    }

}
