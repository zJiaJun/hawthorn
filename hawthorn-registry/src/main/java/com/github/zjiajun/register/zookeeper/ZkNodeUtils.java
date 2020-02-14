package com.github.zjiajun.register.zookeeper;

import com.github.zjiajun.hawthorn.constants.HawthornConstants;
import com.github.zjiajun.hawthorn.registry.RegisterInfo;
import com.google.common.base.Splitter;

import java.util.Map;

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

    public static String toData(RegisterInfo registerInfo) {
        String zkDataMacro = "app=%s&group=%s&service=%s&host=%s&port=%s";
        return String.format(zkDataMacro,
                registerInfo.getApp(), registerInfo.getGroup(), registerInfo.getService(),
                registerInfo.getHost(), registerInfo.getPort());
    }

    public static RegisterInfo parseData(String zkNodeData) {
        Map<String, String> providerMap = Splitter.on("&").withKeyValueSeparator("=").split(zkNodeData);

        return RegisterInfo.builder().app(providerMap.get("app"))
                .group(providerMap.get("group"))
                .service(providerMap.get("service"))
                .host(providerMap.get("host"))
                .port(Integer.parseInt(providerMap.get("port"))).build();


    }
}
