package com.github.zjiajun.register.zookeeper;

import com.github.zjiajun.hawthorn.constants.HawthornConstants;
import com.github.zjiajun.hawthorn.registry.RegisterInfo;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/13 11:35
 */
public final class ZkNodeUtils {

    public static String buildGroupPath(RegisterInfo registerInfo) {
        return HawthornConstants.ZK_ROOT_PATH + HawthornConstants.PATH_SEPARATOR + registerInfo.getApp() + HawthornConstants.PATH_SEPARATOR + registerInfo.getGroup();
    }

    public static String buildServicePath(RegisterInfo registerInfo) {
        return buildGroupPath(registerInfo) + HawthornConstants.PATH_SEPARATOR + registerInfo.getService();
    }

    public static String buildServiceTypePath(RegisterInfo registerInfo, String type) {
        return buildServicePath(registerInfo) + HawthornConstants.PATH_SEPARATOR + type;
    }

    public static String buildCompletePath(RegisterInfo registerInfo, String type) {
        return buildServiceTypePath(registerInfo, type) + HawthornConstants.PATH_SEPARATOR + registerInfo.getHost() + ":" + registerInfo.getPort();
    }
}
