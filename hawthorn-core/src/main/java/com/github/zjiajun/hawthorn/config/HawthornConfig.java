package com.github.zjiajun.hawthorn.config;

import com.github.zjiajun.hawthorn.constants.HawthornConstants;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Objects;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/12 11:24
 */
public final class HawthornConfig {

    private HawthornConfig() { }

    private static HawthornConfig instance = new HawthornConfig();

    public static HawthornConfig singleton() {
        return instance;
    }

    private static Config config = ConfigFactory.load("config");

    public Config originalConfig() {
        return config;
    }

    public String zkAddress() {
        return getString("hawthorn.registry.address", HawthornConstants.DEFAULT_ZK_ADDRESS);
    }

    public int zkSessionTimeout() {
        return getInt("hawthorn.registry.sessionTimeout", HawthornConstants.DEFAULT_ZK_SESSION_TIMEOUT);
    }

    public int zkConnectionTimeout() {
        return getInt("hawthorn.registry.connectionTimeout", HawthornConstants.DEFAULT_ZK_CONNECTION_TIMEOUT);
    }

    private int getInt(String key, int defaultValue) {
        return config.hasPath(key) ? config.getInt(key) : defaultValue;
    }

    private String getString(String key, String defaultValue) {
        Objects.requireNonNull(key, "Config key must not be null");
        return config.hasPath(key) ? config.getString(key) : defaultValue;
    }
}
