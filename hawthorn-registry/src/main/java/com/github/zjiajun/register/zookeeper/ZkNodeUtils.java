package com.github.zjiajun.register.zookeeper;

import com.github.zjiajun.hawthorn.constants.HawthornConstants;
import com.github.zjiajun.hawthorn.registry.ProviderInstance;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/13 11:35
 */
public final class ZkNodeUtils {

    public static String buildGroupPath(ProviderInstance providerInstance) {
        return HawthornConstants.ZK_ROOT_PATH + HawthornConstants.PATH_SEPARATOR + providerInstance.getApp() + HawthornConstants.PATH_SEPARATOR + providerInstance.getGroup();
    }

    public static String buildServicePath(ProviderInstance providerInstance) {
        return buildGroupPath(providerInstance) + HawthornConstants.PATH_SEPARATOR + providerInstance.getService();
    }

    public static String buildServiceTypePath(ProviderInstance providerInstance, String type) {
        return buildServicePath(providerInstance) + HawthornConstants.PATH_SEPARATOR + type;
    }

    public static String buildCompletePath(ProviderInstance providerInstance, String type) {
        return buildServiceTypePath(providerInstance, type) + HawthornConstants.PATH_SEPARATOR + providerInstance.getHost() + ":" + providerInstance.getPort();
    }
}
